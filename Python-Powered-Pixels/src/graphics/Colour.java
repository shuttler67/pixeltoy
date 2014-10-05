package graphics;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;

public class Colour {
	private static int[] currentColour = new int[]{0, 0, 0, 255};
	private static double[] backgroundColour = new double[]{1, 1, 1};

	public static void useColour(double r, double g, double b, double a) {
		double red = r / 255d;
		double green = g / 255d;
		double blue = b / 255d;
		double alpha = a / 255d;
		glColor4d(red, green, blue, alpha);
		currentColour = new int[]{(int) r, (int) g, (int) b, (int) a};
	}
	
	public static void resetColour() {
		useColour(255, 0, 0, 255);
	}
	
	public static Color getCurrentColour() {
		return new Color(currentColour[0], currentColour[1], currentColour[2], currentColour[3]);
	}

	public static void useCurrentSelectedColour() {
		double red = (double)currentColour[0] / 255d;
		double green = (double)currentColour[1] / 255d;
		double blue = (double)currentColour[2] / 255d;
		double alpha = (double)currentColour[3] / 255d;
		glColor4d(red, green, blue, alpha);
	}

    public static void setBackgroundColour(double r, double g, double b) {
        backgroundColour[0] = r / 255d;
        backgroundColour[1] = g / 255d;
        backgroundColour[2] = b / 255d;
    }

    public static void useBackgroundColour() {
        glColor4d(backgroundColour[0], backgroundColour[1], backgroundColour[2], 1);
        int width = Display.getWidth();
        int height = Display.getHeight();
        GraphicsController.drawRectangle(width/2, height/2, width, height, 0);
        resetColour();
    }
}
