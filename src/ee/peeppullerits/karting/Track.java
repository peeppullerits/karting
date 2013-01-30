package ee.peeppullerits.karting;

import java.util.ArrayList;
import java.util.HashMap;

public class Track {
	public double[] values;
	public int id;

	Track(double[] values, int id) {
		this.values = values;
		this.id = id;
	}

	public ArrayList<Vector> getPoints() {
		ArrayList<Vector> res = new ArrayList<Vector>();
		
		Vector curr = new Vector();
		res.add(curr);
		
		for (int i = 0; i < values.length; i += 2) {
			double dirDeg = values[i];
			double len = values[i+1];

			curr = curr.translate(len, Math.toRadians(dirDeg));
			res.add(curr);
		}
		
		//System.out.println("points:" + res);
		return res;
	}

}
