package pixeltoy;

import java.util.Random;

import org.lwjgl.opengl.Display;
import core.GameController;
import core.Input;
import core.Main;

public class PixelToy {
	public static Drawing graphics;
	public static Input input;
    public static Listening audio;
	
	private static final Random random = new Random();
	private static final GameController controller = new GameController();
	
	public static void init() {
		Main.init();
		graphics = new Drawing();
		input = new Input();
        audio = new Listening();
		random.setSeed(System.nanoTime());
	}
	
	public static void quit() {
		controller.quit();
	}
	
	public static void newFrame() {
		controller.newFrame();
	}
	
	public static double random() {
		return random.nextDouble();
	}
	
	public static int getScreenWidth() {
		return Display.getWidth();
	}
	
	public static int getScreenHeight() {
		return Display.getHeight();
	}
}
