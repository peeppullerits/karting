package ee.peeppullerits.karting;

public class KartConfig {

	public double horsepower;
	public double jumpiness;
	int maxRpm;
	public double rpmAccel;
	public double turnRadius;
	public double brakePower;
	public double brakeJumpiness;
	
	KartConfig(int num) {
		switch (num) {
		case 1:
			horsepower = 50;
			maxRpm = 3500;
			jumpiness = 1.6;
			rpmAccel = 60;
			turnRadius = 25;
			brakePower = 1.5;
			brakeJumpiness = 1.5;
			break;
		case 2:
			horsepower = 100;
			maxRpm = 4500;
			jumpiness = 0.8;
			rpmAccel = 200;
			turnRadius = 30;
			brakePower = 1.5;
			brakeJumpiness = 0.25;
			break;
		default:
			throw new IllegalStateException("unknown kart");
		}
	}

}
