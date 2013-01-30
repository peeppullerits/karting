package ee.peeppullerits.karting;

public class Referee {

	public static void check(Track track, Kart kart) {
		Vector cp = kart.checkpoint;
		if (cp == null)
			return;
		double dist = kart.location.distance(cp);
		if (dist < 20.0) {
			kart.cpTime = System.currentTimeMillis();
			//System.out.println("REACHED CHECKPOINT #" + kart.cpNum + "; time " + (kart.cpTime - kart.startTime)/1000.0 + " s");
			kart.cpNum++;
			
			if (kart.cpNum >= track.getPoints().size()) {
				kart.checkpoint = null;
				kart.finishRace(track);
				kart.startRace(track);
			} else {
				kart.checkpoint = track.getPoints().get(kart.cpNum);
			}
		}
	}

}
