package ee.peeppullerits.karting;

import org.lwjgl.opengl.GL11;

public class Hud {

	public static void render(Kart kart) {
		projection();
		renderBrake(kart);
		renderGasPedal(kart);
		renderRpm(kart);
		renderSpeed(kart);
	}


	private static void renderBrake(Kart kart) {
		double pedal = kart.brakePedal;
		double h = pedal*100 + 1;
		//System.out.println("pedal: " + pedal + "; speed " + kart.speed);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3d(1.0, 0.0,  0.0);
		GL11.glVertex2d(0.0,  0);
		GL11.glVertex2d(5.0,  0);
		GL11.glVertex2d(5.0,  h);
		GL11.glVertex2d(0.0,  h);
		GL11.glEnd();
	}
	
	private static void renderGasPedal(Kart kart) {
		double pedal = kart.gasPedal;
		double h = pedal*100 + 1;
		//System.out.println("pedal: " + pedal + "; speed " + kart.speed);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3d(0.0, 0.0,  1.0);
		GL11.glVertex2d(6.0,  0);
		GL11.glVertex2d(11.0,  0);
		GL11.glVertex2d(11.0,  h);
		GL11.glVertex2d(6.0,  h);
		GL11.glEnd();
	}
	
	private static void renderRpm(Kart kart) {
		double speed = kart.engine.engineRpm;
		double h = speed / 100 + 1;
		//System.out.println("pedal: " + pedal + "; speed " + kart.speed);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3d(0.0, 1.0,  0.0);
		GL11.glVertex2d(80.0,  0);
		GL11.glVertex2d(85.0,  0);
		GL11.glVertex2d(85.0,  h);
		GL11.glVertex2d(80.0,  h);
		GL11.glEnd();
		
	GL11.glBegin(GL11.GL_QUADS);
	
	double max = kart.config.maxRpm / 100;
		
		GL11.glColor4d(0.5, 0.0,  0.0, 0.5);
		GL11.glVertex2d(80.0,  max + 1);
		GL11.glVertex2d(85.0,  max  +1);
		GL11.glVertex2d(85.0,  max +1.5);
		GL11.glVertex2d(80.0,  max + 1.5);
		GL11.glEnd();
	}

	
	private static void renderSpeed(Kart kart) {
		double speed = kart.speed;
		double h = speed * 100 + 1;
		//System.out.println("pedal: " + pedal + "; speed " + kart.speed);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3d(0.0, 0.0,  1.0);
		GL11.glVertex2d(90.0,  0);
		GL11.glVertex2d(95.0,  0);
		GL11.glVertex2d(95.0,  h);
		GL11.glVertex2d(90.0,  h);
		GL11.glEnd();
	}

	private static void projection() {
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 100, 0, 100, 1, -1);
			
			
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
		
	}

}
