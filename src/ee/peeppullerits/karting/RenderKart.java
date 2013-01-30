package ee.peeppullerits.karting;

import org.lwjgl.opengl.GL11;

public class RenderKart {

	static void renderKart(Kart kart) {
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		
		GL11.glTranslated(kart.location.x, kart.location.y, (float) 0.0f);
		GL11.glRotated(Math.toDegrees(kart.headingRad) + 90, 0.0, 0.0, 1.0);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.0f, 0.0f, 0.5f);
		
		GL11.glVertex2f(-0.5f, -1.0f);
		GL11.glVertex2f(0.5f, -1.0f);
		GL11.glVertex2f(0.5f, 1.5f);
		GL11.glVertex2f(-0.5f, 1.5f);
		
		GL11.glColor3f(0.5f, 0.0f, 0.0f);
		
		GL11.glVertex2f(-0.25f, -1.5f);
		GL11.glVertex2f(-0.25f, -1.0f);
		GL11.glVertex2f(0.25f, -1.0f);
		GL11.glVertex2f(0.25f, -1.5f);
		GL11.glEnd();
		
		
		renderAxle(kart.back, kart);
		renderAxle(kart.front, kart);
		
		/*GL11.glColor3f(1.0f, 0.0f, 1.0f);
		drawPointAt(kart.getLocation().add(kart.getHeadingVector()), 1.0);
		*/
		
		// back wheels
		if (kart.back.grip < 1.0) {
			GL11.glColor3f((float) (0.5f + ((1.0f-kart.back.grip) / 0.5f)), 0.5f, 0.5f);
		} else {
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
		}
		
		Vector wheel = new Vector(0.0, kart.wheelBase / 2.0);
		wheel.x = 0.5;
		Render.drawPointAt(wheel, 0.1);
		
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		Render.drawPointAt(kart.front.location, 0.1);
		Render.drawPointAt(kart.back.location, 0.1);
		GL11.glPopMatrix();

		wheel.x = -0.5;
		Render.drawPointAt(wheel, 0.1);
	
		
		// front left
		if (kart.front.grip < 1.0) {
			GL11.glColor3f((float) (0.5f + ((1.0f-kart.front.grip) / 0.5f)), 0.5f, 0.5f);
		} else {
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
		}
		
		wheel.y = -kart.wheelBase / 2.0;
				wheel.x = -0.5;
				renderFrontWheel(kart, wheel);
				
				wheel.x = 0.5;
				renderFrontWheel(kart, wheel);
				// front right
			
				wheel.x = 0;
		/*Vector dir = wheel.deduct(kart.getSteeringVector()).multiply(2);
		GL11.glColor3f(0.9f, 0.9f, 0.9f);
		drawPointAt(dir, 0.2);
			*/	
				
			
		
		GL11.glPopMatrix();
	}

	private static void renderAxle(Axle front, Kart kart) {
		GL11.glPushMatrix();
		
		GL11.glLoadIdentity();
		
		Vector src = front.location; //.deduct(kart.getLocation());
		//Vector dest = src.add(front.velocity);
		//src = src.add(front.velocity);
		//kart.getWheel();
		//Vector dest = src.add(new Vector(-0.1, 0.0));
		Vector dest = src.add(front.velocity);
		
		//System.out.println("axle at " + front.location + "; velocity " + front.velocity);
		
		//angle = Mouse.getX() % 360;
		GL11.glColor3d(0.9, 0.9, 0.9);
		//GL11.glTranslated(dir.x, dir.y, 0.0);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2d(dest.x, dest.y-0.2);
		GL11.glVertex2d(dest.x, dest.y+0.2);
		GL11.glVertex2d(src.x, src.y+0.2);
		GL11.glVertex2d(src.x, src.y-0.2);
		
		GL11.glVertex2d(dest.x, dest.y-0.3);
		GL11.glVertex2d(dest.x, dest.y+0.3);
		GL11.glVertex2d(dest.x+0.1, dest.y+0.3);
		GL11.glVertex2d(dest.x+0.1, dest.y-0.3);
		
		GL11.glEnd();
		
		GL11.glPopMatrix();
		
	}

	private static void renderFrontWheel(Kart kart, Vector wheel) {
		double angle = Math.toDegrees(kart.steerAngle);// * 3;
		
		GL11.glPushMatrix();
		
		
		//angle = Mouse.getX() % 360;
		GL11.glTranslated(wheel.x, wheel.y, 0.0);
		
		GL11.glTranslated(0.05, 0.1, 0.0);
		GL11.glRotated(angle, 0.0, 0.0, 1.0);
		GL11.glTranslated(-0.05, -0.1, 0.0);
		
		
		
		
		
		//System.out.println("steerangle:" + angle);
		
		
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2d(-0.1, 0.0);
		GL11.glVertex2d(-0.1, -0.2);
		GL11.glVertex2d(0.1, -0.2);
		GL11.glVertex2d(0.1, 0.2);
		
		GL11.glEnd();
		
		GL11.glPopMatrix();
	}
}
