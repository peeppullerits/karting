package ee.peeppullerits.karting;

import org.lwjgl.input.Keyboard;

public class KbActions {


	static int [] keys;

	
	static void readKeyboard(Kart kart) {
		for (int key : keys) {
			if (Keyboard.isKeyDown(key)) {
				keyIsDownRepeat(key, kart);
			}
		}
	
		readKeyboardEvents();
		
		
	}

	private static void keyIsDownRepeat(int key, Kart kart) {
		switch (key) {
	
		/*case Keyboard.KEY_UP:
			kart.accelerate();
			break;
		case Keyboard.KEY_DOWN:
			kart.brake();
			break;
		case Keyboard.KEY_R:
			kart.reverse();
			break;
		case Keyboard.KEY_LEFT:
			kart.turn(1.0);
			break;
		case Keyboard.KEY_RIGHT:
			kart.turn(-1.0);
			break;*/
		}
	}

	private static void keyIsDown(int key, Kart kart) {
		switch (key) {

		case Keyboard.KEY_ESCAPE:
			System.exit(0);
		case Keyboard.KEY_1:
			Camera.num = 1;
			break;
		case Keyboard.KEY_2:
			Camera.num = 2;
			break;
		case Keyboard.KEY_R:
			System.out.println("reset");
			Main.createKart();
			break;
		case Keyboard.KEY_K:
			Main.switchKart();
			break;
		case Keyboard.KEY_E:
			System.out.println("toggling ignition");
			kart.engine.toggleIgnitian();
			break;
			
		}
	}

	
	private static void readKeyboardEvents() {
		while (Keyboard.next()) {
			int key = Keyboard.getEventKey();
			boolean state = Keyboard.getEventKeyState();
			//System.out.println("state: " + state);
			if (state == true)
				keyIsDown(key, Main.kart);
		}
		
	}


}
