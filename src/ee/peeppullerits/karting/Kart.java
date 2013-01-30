package ee.peeppullerits.karting;

import org.lwjgl.input.Mouse;

public class Kart {
	public double speed;
	// the direction it is moving in
	public Vector velocity;
	public Vector location;
	public double headingRad = Math.toRadians(90.0);
	public double wheelBase = 2.5f;
	public double steerAngle = Math.toRadians(0);
	public double gasPedal;
	double brakePedal;
	
	Vector checkpoint;
	long cpTime;
	long startTime;
	Vector finishCp;
	
	Engine engine;
	Axle front;
	Axle back;
	KartConfig config;
	public int cpNum = 0;


	Kart(KartConfig config) {
		construct(new Vector(0.0, 0.0), config);
	}
	
	Kart(Vector loc, KartConfig config) {
		construct(loc, config);

	}
	
	private void construct(Vector loc, KartConfig config) {
		this.config = config;
		this.engine = new Engine(this, config);
		this.location = loc;
		this.front = new Axle(this, getWheel());
		this.back = new Axle(this, getWheel().multiply(-1));
	}
	
	public void startRace(Track track) {
		cpNum = 0;
		checkpoint = new Vector(track.getPoints().get(0));
		startTime = System.currentTimeMillis();
		double deg = track.values[2];
		/*headingRad = Math.toDegrees(deg);
		System.out.println("set heading to "+ headingRad);*/
		finishCp = track.getPoints().get(track.getPoints().size()-1);
	}
	
	public void finishRace(Track track) {
		long endTime = System.currentTimeMillis();
		
		double lapTime = (endTime-startTime) / 1000.0;
		System.out.println("Lap time: " + lapTime + "s");
		Db.query("INSERT INTO lap_times VALUES(" + track.id + "," +lapTime + ")");
	}

	public void runPhysics() {
		calculateSteeringPosition();
		engine.calculateEngineRpm();
		calculateSpeed();
		
		front.calculateVelocity(true);
		front.move();
		back.calculateVelocity(false);
		back.move();
		move();
	}
	
	private void move() {
		Vector newLoc = new Vector();
		//System.out.println("axles at: " + front.location + " & " + back.location);
		newLoc = newLoc.add(front.getLocation());
		newLoc = newLoc.add(back.getLocation());
		//System.out.println("step 1: " + newLoc);
		//frontWheel.add(backWheel);
		//System.out.println("loc step 1 " + location);
		newLoc = newLoc.divide(2.0);
		//System.out.println("step 2: " + newLoc);
		
		Vector oldLoc = location;
		location = newLoc;
		
		/*double diffY = front.location.y - back.location.y;
		double diffX = front.location.x - back.location.x;*/
		Vector diff = newLoc.deduct(oldLoc);
		
		headingRad = Math.atan2(diff.y, diff.x);
		//if (speed > 0)
			//System.out.println("old loc:"+ oldLoc +"; new loc:" + location + ";heading now: " + headingDeg);
	}


	private void calculateSteeringPosition() {
		/*double deg = this.steeringRotateSpeed;
		
		deg *= 1.0 + speed * 1.5;
		if (Math.abs(steerAngle) > 45) {
			deg *= 1.25;
		}
		steerAngle += Math.toRadians(deg);
		//System.out.println("angle now: " + steerAngle + "; checking < " + Math.toRadians(45));
		if (steerAngle > Math.toRadians(45.0))
			steerAngle = Math.toRadians(45.0);
		if (steerAngle < Math.toRadians(-45.0))
			steerAngle = Math.toRadians(-45.0);
		*/
		
		double deg = -((Mouse.getX() / (double)Main.viewportWidth) - 0.5) * config.turnRadius;
		deg = deg * Math.abs(deg) * 0.02;
		
		double jumpiness = speed * speed * config.jumpiness;
		
		if (speed > 0.00001)
			jumpiness += (brakePedal * brakePedal * 2);
		
		deg += -jumpiness + Math.random() * jumpiness * 2;
		
		//System.out.println("deg: " + deg);
		steerAngle = Math.toRadians(deg);
		/*
		this.steeringRotateSpeed *= 0.75;
		if (Math.abs(this.steeringRotateSpeed) < 0.01) {
			this.steeringRotateSpeed = 0.0;
		}
		System.out.println("rotate speed now: " + steeringRotateSpeed);*/
	}

	private void calculateSpeed() {
		double ratio = (Mouse.getY() / (double) Main.viewportHeight) - 0.5;
		ratio *= 2.0;
		gasPedal = ratio;
		if (gasPedal < 0)
			gasPedal = 0.0;
	
		ratio = 0.5 - (Mouse.getY() / (double) Main.viewportHeight);
		ratio *= 2.0;
		brakePedal = ratio;
		
	
		//System.out.println("brake ratio: " + ratio);
		if (brakePedal < 0)
			brakePedal = 0.0;
		
		
		double drag = speed * speed;
		drag += engine.getDrag() / 1000;
		//System.out.println("engine drag:" + drag);
		
		double friction = speed * speed * (8.0 + Math.random() * 2.0);
		
		double braking = brakePedal * brakePedal * config.brakePower;
		double jumpiness = (speed * speed);
		if (speed > 0.0000001)
			jumpiness += (brakePedal * brakePedal);
		jumpiness *= config.brakeJumpiness * 0.2;
		
		braking *= (1.0 - jumpiness) + Math.random() * jumpiness;
		
		double power = engine.getPower();
		if (false && power > 0)
			System.out.println("engineRPM: " + engine.engineRpm + "; power: " + power + "; braking " + braking + " drag " + drag) ;
		double accel = power - drag - friction - braking;
		speed += accel / 1000;
		if (speed < 0)
			speed = 0;
	}


	public Vector getSteeringVector() {
		return Vector.getFromAngle(headingRad + steerAngle);
	}

	Vector getWheel() {
		Vector res = Vector.getFromAngle(headingRad);
		
		res.x *= wheelBase / 2.0;
		res.y *= wheelBase / 2.0;
		
		return res;
	}

	Vector getHeadingVector() {
		return Vector.getFromAngle(headingRad);
	}
	

	public Vector getLocation() {
		return new Vector(location);
	}



}
