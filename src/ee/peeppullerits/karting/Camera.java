package ee.peeppullerits.karting;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Camera {
	public static int num = 2;

	static void projections(int viewportWidth, int viewportHeight) {
		GL11.glLoadIdentity();

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		//GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glLoadIdentity();
		GLU.gluPerspective( 45.0f, (float)viewportWidth/(float)viewportHeight, 0.5f, 5000.0f );
		Camera.set(Main.kart);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	public static void set(Kart kart) {
		switch (num) {
			case 1:
				setTrackView(kart);
			break;
			case 2:
				setKartView(kart);
				break;
		}
		
	}

	private static void setKartView(Kart kart) {
		Vector loc = kart.getLocation();
		Vector head = kart.getHeadingVector();
		head = head.multiply(10);
		Vector behind = loc.deduct(head);
		//GL11.glRotated(-kart.headingDeg + 90, 0.0, 0.0, 1.0);
		//GL11.glOrtho(x - 10, x + 90, y - 45, y + 45, 1000, -1000);
		
		//System.out.println("mouse " + Mouse.getX() + " " + Mouse.getY());
		float mx = Mouse.getX() - 400.0f;
		float my=  Mouse.getY() - 300.0f;
		//System.out.println("mouse " + (mx/400.0f) + " " + (my/300.0f));
		GLU.gluLookAt((float)behind.x, (float)behind.y, 3, 
		(float)loc.x, (float) loc.y, 0, 
		0, 0, 1);
		
		//GL11.glTranslated(kart.location.x, kart.location.y, 0);
		
	}

	private static void setTrackView(Kart kart) {
		float x = (float) kart.location.x;
		float y =  (float) kart.location.y;
		
		//GL11.glTranslated(kart.location.x, kart.location.y, 0);
		float zoom = 50 + (float) (kart.speed * kart.speed * 500f); // + Math.abs((Mouse.getY() - 300) * 0.1f);
		//GL11.glOrtho(x-zoom, x+zoom, y-zoom, y+zoom, 1000, -1000);
		
		//y += zoom / 4;
		
		GLU.gluLookAt(x, y, zoom,
		x, y, 0, 
		0, 1, 0);
	}
	
	
}
