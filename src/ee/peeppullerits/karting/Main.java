package ee.peeppullerits.karting;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Main {

	public static int viewportWidth = 800;
	public static int viewportHeight = 600;

	public static Kart kart;

	public static Track track;
	private static int selectedKart;
	
	public static void main(String[] args) {
		KbActions.keys = new int[] { Keyboard.KEY_UP, Keyboard.KEY_DOWN, Keyboard.KEY_LEFT,
				Keyboard.KEY_RIGHT, };
		selectedKart = 2;
		
		Db.connect();
		Db.createTables();
		Db.addLaunchTime();
		
		double[] t1 = new double[] { 90, 100, -40, 60, 90, 150, 35, 60, 90, 100, 45, 50, 90, 50, -60, 20, -120, 20, -90, 30, 180, 10, 90, 150 };
		double[] t2 = new double[] { 90, 100, 45, 25, 0, 100, -45, 50, -90, 100, 180, 50, 190, 30, 200, 20, 210, 30, -90, 50, 180, 50, 90, 50, 65, 50};
		double[] t3 = new double[] { 90, 50, 45, 50, 0, 50, -45, 50, -90, 50, -135, 50, 180, 50, 135, 50 }; 
		double[] t4 = new double[] { 90, 150, -45, 50, 45, 10, 135, 50, 0, 150, -110, 180, 175, 100 };
		Track track1 = new Track(t1, 1);
		Track track2 = new Track(t2, 2);
		Track track3 = new Track(t3, 3);
		Track track4 = new Track(t4, 4);
		track = track3;
		createKart();
	
		setUp();
		gameLoop();
		
		Db.close();
	}
	
	static void createKart() {
		
		KartConfig kartConfig = new KartConfig(selectedKart);
		kart = new Kart(new Vector(0.0, 0.0), kartConfig);
		kart.startRace(track);
	}

	/**
	 * Rudimentary OpenGL setup with LWJGL. Use perspective
	 * projection with a field of view of 45 degrees. 
	 */
	public static void setUp() 
	{
		// set up LWJGL display
		try
		{
			Display.setDisplayMode(new DisplayMode(viewportWidth, viewportHeight));
			Display.create();
		}
		catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		GL11.glViewport(0, 0, viewportWidth, viewportHeight);

	}
 
	/**
	 * Game loop is basically an infinite loop that runs
	 * until the window is closed. It calls the abstract
	 * render() method which is to be implemented by the
	 * specific example class. 
	 */
	public static void gameLoop()
	{
		
		
		while (!Display.isCloseRequested())
		{
			kart.runPhysics();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			Camera.projections(viewportWidth, viewportHeight);
			Render.render();
			Hud.render(kart);
			KbActions.readKeyboard(kart);
			Referee.check(track, kart);
			Display.update();
			Display.sync(60);
		}
 
		Display.destroy();
	}

	public static void switchKart() {
		selectedKart = selectedKart % 2 + 1;
		createKart();
		System.out.println("Switched to kart " + selectedKart);
		
	}



}
