package ee.peeppullerits.karting;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Render {

	static void render() {
		features();
		renderGround();
		renderTrack();
		RenderKart.renderKart(Main.kart);
	}
	
	private static void renderGround() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.0f, 0.3f, 0.0f);
		
		double size = 50000;
		GL11.glVertex2d(-size, -size);
		GL11.glVertex2d(size, -size);
		GL11.glVertex2d(size, size);
		GL11.glVertex2d(-size, size);
		GL11.glEnd();		
	}

	private static void features() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glShadeModel (GL11.GL_SMOOTH);
		GL11.glClearColor (0f, 0.0f, 0.9f, 0f);
	}

	private static void renderTrack() {
		
		Vector prev = null;
		boolean raised = false;
		for (Vector point : Main.track.getPoints()) {
			if (prev != null) {
				//System.out.println("segment from " + prev + " to " + point);
				renderTrackSegment(prev, point, 10.0, raised);
				raised = !raised;
			}
			
			prev = point;
		}
	}
	

	private static void renderTrackSegment(Vector p1, Vector p2, double width, boolean raised) {
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		//System.out.println("starting from " + p1);
		
		GL11.glTranslated(p1.x, p1.y, 0.0);
		//System.out.println("angle: " + -p1.getAngle(p2));
		GL11.glRotated(-p1.getAngle(p2), 0.0, 0.0, 1.0);
		

		GL11.glColor3d(0.2, 0.2, 0.2);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		double offset = width / 2;
		
		//double angleDeg = p1.getAngle(p2);
		//Vector sideLeft1 = p1.translate(offset,  Math.toRadians(angleDeg + 90));
		
		if (raised)
			GL11.glTranslated(0, 0, 0.2);
		
		//System.out.println("p1: " + p1 + " p2 " + p2);
		GL11.glVertex2d(-offset, 0.0);
		GL11.glVertex2d(offset, 0.0);
		GL11.glVertex2d(offset, p1.distance(p2));
		GL11.glVertex2d(-offset, p1.distance(p2));
		
		GL11.glColor3d(0.8,  0.0,  0.0);
		GL11.glVertex2d(-offset-1, 0.0);
		GL11.glVertex2d(-offset, 0.0);
		GL11.glVertex2d(-offset, p1.distance(p2));
		GL11.glVertex2d(-offset-1, p1.distance(p2));
		
		GL11.glVertex2d(offset, 0.0);
		GL11.glVertex2d(offset+1, 0.0);
		GL11.glVertex2d(offset+1, p1.distance(p2));
		GL11.glVertex2d(offset, p1.distance(p2));
		GL11.glEnd();
		GL11.glPopMatrix();
		
		
		setPointColor(p1);
		drawPointAt(p1, 2.0);
		
		setPointColor(p2);
		drawPointAt(p2, 2.0);
	}


	private static void setPointColor(Vector p1) {
		if (Main.kart.checkpoint != null && Main.kart.checkpoint.equals(p1)) {
			GL11.glColor3d(0.0, 1.0, 0.0);
		} else if (Main.kart.finishCp.equals(p1)) {
			GL11.glColor3d(1.0, 1.0, 0.0);
		} else {
			GL11.glColor3d(1.0, 0.0, 0.0);
		}
	}

	static void drawPointAt(Vector vec, double size) {
		double x = vec.x;
		double y = vec.y;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0.0);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2d(-size, 0.0);
		GL11.glVertex2d(-size, -size);
		GL11.glVertex2d(size, -size);
		GL11.glVertex2d(size, size);
		
		GL11.glEnd();
		
		GL11.glPopMatrix();
		
	}
 
}
