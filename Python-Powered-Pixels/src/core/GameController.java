package core;


import audio.AudioController;
import org.lwjgl.opengl.Display;

public class GameController {
	
	public void newFrame() {
		Main.newBlankFrame();
	}
	
	public void quit() {
        Display.destroy();
        AudioController.killALData();
		System.exit(0);
	}
}
