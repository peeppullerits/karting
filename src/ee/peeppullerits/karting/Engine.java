package ee.peeppullerits.karting;

public class Engine {
	double engineRpm;
	private boolean running;
	private boolean starting;
	private Kart kart;
	private boolean stopping;
	private KartConfig config;
	
	Engine(Kart kart, KartConfig config) {
		this.kart = kart;
		this.config = config;
	}

	void calculateEngineRpm() {
		if (this.starting == true) {
			if (engineRpm > 600) {
				starting = false;
				running = true;
				System.out.println("engine Started");
				return;
			}
			
			engineRpm += 50;
			//System.out.println("starting, engineRpm now" + engineRpm);
			return;
		}
		
		if (this.running == true && stopping == false) {
			//System.out.println("engineRpm: " + engineRpm + "; pedal: " + kart.gasPedal);
			
			if (engineRpm < 600) {
				// idling
				double diff = 600 - engineRpm;
				this.engineRpm += (diff / 2) + (Math.random() * diff / 2);
				/*if (this.engineRpm > 650)
					engineRpm = 650;
				if (engineRpm < 550)
					engineRpm = 550;*/
				//System.out.println("idling, rpm now " + engineRpm);
			}
			
			//System.out.println("running, gaspedal: " + kart.gasPedal);
			double factor = kart.gasPedal;
			double incr = 600 * factor;
			if (incr > config.rpmAccel)
				incr = config.rpmAccel;
			engineRpm += incr;

				//System.out.println("factor: " + factor + "; RPM now: " + engineRpm);
			
		
			// near max power, jumping around a lot
			if (engineRpm > config.maxRpm * 0.95)
				engineRpm *= 0.90 + Math.random() * 0.06;
			else
				engineRpm *= 0.95 + Math.random() * 0.03;
			
			if (engineRpm > config.maxRpm) {
				// engine hit the limiter
				double diff = engineRpm - config.maxRpm;
				engineRpm -= (diff/3) + Math.random() * (diff / 4);
			}
			
			
			
		}
		
		if (stopping == true) {
			engineRpm *= 0.8;
		}
		
		if (engineRpm < 100)
			engineRpm = 0;
		
		
		if (engineRpm == 0 && (running || stopping || starting)) {
			System.out.println("engine stopping.. rpm=" + engineRpm);
			stopping = false;
			starting = false;
			running = false;
		}
		
		
		
	}

	public void startEngine() {
		if (engineRpm < 600) {
			starting = true;
			stopping = false;
			engineRpm = 50;
		} else {
			running = true;
		}
	}

	public void toggleIgnitian() {
		if (starting == true || stopping == true)
			return;
		
		System.out.println("toggle; running: " + running);
		if (running == false) {
			startEngine();
		} else {
			stopping = true;
			starting = false;
			//running = false;
		}
		
	}

	public double getPower() {
		if (engineRpm < 600)
			return 0;
		
		double rpmAbove = engineRpm - 600;
		rpmAbove /= 1000;
		return rpmAbove*rpmAbove / config.horsepower / 10.0;
	}

	public double getDrag() {
		return 1000 - engineRpm;
	}
}
