package ee.peeppullerits.karting;

public class Vector {

	public double x;
	public double y;
	
	public Vector() {
		
	}
	
	boolean equals(Vector v) {
		if (v.x != x)
			return false;
		if (v.y != y)
			return false;
		
		return true;
	}
	
	public String toString() {
		return "[" + x + ";" + y + "]";
	}
	
	public Vector(double d, double e) {
		this.x = d;
		this.y = e;
	}
	public Vector(Vector location) {
		this.x = location.x;
		this.y = location.y;
	}

	public Vector add(Vector wheel) {
		Vector res = new Vector();
		res.x = x + wheel.x;
		res.y = y +wheel.y;
		return res;
	}
	public Vector deduct(Vector wheel) {
		Vector res = new Vector();
		res.x = x - wheel.x;
		res.y = y -wheel.y;
		return res;
	}
	
	public Vector multiply(double d) {
		Vector res = new Vector();
		res.x = this.x * d;
		res.y = this.y * d;
		return res;
	}
	public Vector divide(double d) {
		Vector res = new Vector();
		res.x = x / d;
		res.y = y / d;
		return res;
		
	}

	public Vector translate(double len, double dirRad) {
		Vector res = new Vector(x, y);
		res.x += Math.cos(dirRad) * len;
		res.y += Math.sin(dirRad) * len;
		return res;
	}
	
	public double getAngle(Vector target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.x - x, target.y - y));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

	public Vector add(double d) {
		Vector res = new Vector();
		res.x = x + d;
		res.y = y +d;
		return res;
	}
	
	public Vector deduct(double d) {
		Vector res = new Vector();
		res.x = x - d;
		res.y = y -d;
		return res;
	}

	public double distance(Vector p2) {
		double diffX = p2.x - x;
		double diffY = p2.y - y;
		double dist = Math.sqrt(diffX * diffX + diffY * diffY);
		return dist;
	}
	
	public static Vector getFromAngle(double angleRad) {
		double x = Math.cos(angleRad);
		double y = Math.sin(angleRad);
		
		Vector res = new Vector();
		res.x = x;
		res.y = y;
		return res;
	}

	public Vector normalize() {
		Vector res = new Vector();
		if (x > 0)
			res.x = x / Math.abs(x);
		else
			x = 0;
		if (y > 0)
			res.y = y / Math.abs(y);
		else
			y = 0;
		return res;
	}

	public double length() {
		return Math.sqrt(x*x+y*y);
	}

	// degrees
	public double getAngle() {
		return (Math.atan2(y, x) * 180 * Math.PI) % 360; 
	}



}
