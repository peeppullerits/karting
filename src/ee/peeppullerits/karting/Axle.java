package ee.peeppullerits.karting;

public class Axle {

	Vector velocity;
	public Vector location;
	private Kart kart;
	public double grip;

	Axle(Kart kart, Vector location) {
		this.kart = kart;
		this.location = location;
		this.velocity = new Vector(); //Vector.getFromAngle(kart.headingRad);
		System.out.println("created axle at " + location);
	}
	
	public Vector getVelocity() {
		return new Vector(this.velocity);
	}

	void calculateVelocity(boolean steering) {
		/*Vector frontWheel = location.add(getWheel());
		Vector backWheel = location.deduct(getWheel());*/
		
		//System.out.println("speed: " + speed);
		//System.out.println("frontWheel: " + frontWheel + "backwwhel" + backWheel);
		
		Vector dir = null;
		double wheelAngle = 0.0;
		if (steering) {
			wheelAngle = kart.headingRad + kart.steerAngle;
			//dir = kart.getSteeringVector();
			//velocity.normalize();
			//dir = Vector.getFromAngle(kart.headingRad - kart.steerAngle);
			//dir = dir.divide(2);
		} else {
			wheelAngle = kart.headingRad;
			//dir = velocity.normalize();
			//dir = kart.getHeadingVector();
		}
		dir = Vector.getFromAngle(wheelAngle);
		//System.out.println("dir: " + dir);
			
		Vector newVel = dir.multiply(kart.speed);
		//System.out.println("new vel:" + newVel);

		double angle = Math.toDegrees(wheelAngle);
		double angleNew = newVel.getAngle(new Vector(0.0, 0.0));
		//System.out.println("old angle: " + angle +"; new: " + angleNew);
		double angleDiff = Math.abs(angleNew - angle);
		double lenDiff = newVel.length() -  velocity.length();
		//System.out.println("len diff: " + (lenDiff));
		
		grip = 1.0;
		
		if (velocity.length() > 0.1 && /*lenDiff > 0.5 &&*/ angleDiff > 4.0) {
			grip =(4.0 - angleDiff) / 10.0;
			if (grip < 0.8)
				grip = 0.8;
		}
		
		
		//System.out.println("angular movement: " + (angleDiff) +"; grip=" + grip +"; new vel: " + velocity + "; vel len" + velocity.length());
		velocity = applyFriction(velocity, newVel, grip);
		//velocity = newVel;
		
		// momentum/inertia between new and old 
		/*if (velocity != null)
			newVel = newVel.add(velocity);
		velocity = newVel.divide(2.0);*/
		
		//this.velocity = newVel;
		//if (kart.speed > 0)
		//System.out.println("axle velocity now:" + velocity +"; direction vec: " + dir);
	}
	
	private Vector applyFriction(Vector oldVel, Vector newVel, double gripFactor) {
		if (gripFactor < 0.0)
			gripFactor = 0.0;
		
		double len = newVel.length();
		//System.out.println("vector length: " + len);
		Vector res = new Vector(oldVel);
		res.x += newVel.x * gripFactor;
		res.y += newVel.y * gripFactor;
		res = res.divide(2);
		
		/*System.out.println("velocity: " + newVel);
		if (Math.abs(newVel.x) > 0.15) {
			System.out.println("clipping x " + newVel.x);
			newVel.x = (newVel.x / Math.abs(newVel.x)) * 0.1;
			System.out.println("clipped x to " + newVel.x);
		}
		
		if (Math.abs(newVel.y) > 0.15) {
			newVel.y = (newVel.y / Math.abs(newVel.y)) * 0.1;
		}*/
		return res;
	}
	
	void move() {
		//System.out.println("axle at " + location + "; vel " + velocity);
		
		location = location.add(velocity);
		//System.out.println("axle now at " + location);
	}
	 Vector getLocation() {
		 return location;
	}

}
