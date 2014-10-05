package graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.python.core.PyTuple;

import texture.Texture;
import texture.TextureLoader;

public class GraphicsController {
	private static final int fontSize = 18;
	private static final TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.PLAIN, fontSize), false);
	private static final int circleDisplayListID = generateCircleDisplayList(false);
	private static final int squareDisplayListID = generateSquareDisplayList(false);
	private static final int wirecircleDisplayListID = generateCircleDisplayList(true);
	private static final int wiresquareDisplayListID = generateSquareDisplayList(true);
	
	private static boolean isWireFrame;
	
	private static int generateCircleDisplayList(boolean isWire) {
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		
		if (!isWire) {
			glBegin(GL_TRIANGLE_FAN);
			glVertex2d(0, 0);
		} else {
			glBegin(GL_LINE_LOOP);
		}
		for(int angle = 0; angle <= 360; angle++) {
			double x = Math.sin(Math.toRadians(-angle));
			double y = Math.cos(Math.toRadians(-angle));
			glVertex2d(x, y);
		}
		glEnd();
		
		glEndList();
		return listID;
	}
	
	private static int generateSquareDisplayList(boolean isWire) {
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		
		if (!isWire) {
			glBegin(GL_QUADS);
		} else {
			glBegin(GL_LINE_LOOP);
		}
		glTexCoord2d(0, 0);
		glVertex2d(0, 0);
		glTexCoord2d(1, 0);
		glVertex2d(1, 0);
		glTexCoord2d(1, 1);
		glVertex2d(1, 1);
		glTexCoord2d(0, 1);
		glVertex2d(0, 1);
		glEnd();
		
		glEndList();
		return listID;
	}
	
	public static void drawRectangle(double x, double y, double width, double height, double rotation) {
		glPushMatrix();
		
		glTranslated(x, y, 0);
		glRotated(-rotation, 0, 0, 1);
		glScaled(width, height, 1);
		glTranslated(-0.5, -0.5, 0);
		
		if (isWireFrame) {
			glCallList(wiresquareDisplayListID);
		} else {
			glCallList(squareDisplayListID);
		}
		glPopMatrix();
	}
	
	public static void drawCircle(double x, double y, double radius) {
		glPushMatrix();
		glScaled(radius, radius, 1);
		glTranslated(x/radius, y/radius, 0);
		if (isWireFrame) {
			glCallList(wirecircleDisplayListID);
		} else {
			glCallList(circleDisplayListID);
		}
		glPopMatrix();
	}
	
	public static void drawEllipse(double x, double y, double radiusX, double radiusY, double rotation) {
		glPushMatrix();
		
		glTranslated(x, y, 0);
		glRotated(-rotation, 0, 0, 1);
		glScaled(radiusX, radiusY, 1);

		if (isWireFrame) {
			glCallList(wirecircleDisplayListID);
		} else {
			glCallList(circleDisplayListID);
		}
		glPopMatrix();
	}
	
	public static void drawPolygon(double[] points, double rotation) throws Exception {
		if (points.length %2 != 0)
            throw new Exception("point count must be a multiple of two");

		if (!(points.length >= 6))
			throw new Exception("A polygon must have more than two vertices, DUMBASS!");
		
		glPushMatrix();

        double averagex = 0;
        double averagey = 0;

        for (int i=0; i<points.length; i+=2) {
            averagex += points[i];
            averagey += points[i+1];
        }

        averagex /= points.length/2;
        averagey /= points.length/2;

        glTranslated(averagex, averagey, 0);
        glRotated(-rotation, 0, 0, 1);

        if (isWireFrame) {
            glBegin(GL_LINE_LOOP);
        } else {
            glBegin(GL_POLYGON);
        }

        for (int i=0; i<points.length; i+=2) {
            double x = points[i];
            double y = points[i+1];

            x -= averagex;
            y -= averagey;

            glVertex2d(x, y);
        }
        glEnd();

		glPopMatrix();
	}
	public static void drawPolygon(PyTuple p, double rotation) throws Exception {
		double[] points = new double[p.size()];
		
		for (int i = 0; i < p.size(); i++) {
			points[i] = getCoordinate(p.get(i));
		}
		drawPolygon(points, rotation);
	}
	
	public static void drawRegularPolygon(double x, double y, int order, double size, double rotation) {
		glPushMatrix();
		
		glTranslated(x, y, 0);
		glScaled(size, size, 1);
		glRotated(-rotation, 0, 0, 1);
		
		if (!isWireFrame) {
			glBegin(GL_TRIANGLE_FAN);
			glVertex2d(0, 0);
		} else {
			glBegin(GL_LINE_LOOP);
		}
		for(double angle = 0; angle <= 361; angle+= 360d / (double) order) {
			double vx = Math.sin(Math.toRadians(-angle));
			double vy = Math.cos(Math.toRadians(-angle));
			
			glVertex2d(vx, vy);
		}
		glEnd();
		glPopMatrix();
	}
	private static double getCoordinate(Object object) {
		if(object instanceof Integer) {
			return ((Integer) object).doubleValue();
		}
		if(object instanceof Double) {
			return ((Double) object);
		}
        if(object instanceof Float) {
			return ((Float) object).doubleValue();
		}
		throw new IllegalArgumentException("Not a number!");
	}

	public static void drawLine(double x1, double y1, double x2, double y2) {
		glBegin(GL_LINES);
		glVertex2d(x1, y1);
		glVertex2d(x2, y2);
		glEnd();
	}
	
	public static void drawPoint(double x, double y) {
		glBegin(GL_POINTS);
		glVertex2d(x, y);
		glEnd();
	}
	
	public static void drawString(double x, double y, String string, double size, double rotation, boolean centered) {
		glPushMatrix();

		glTranslated(x, y, 0);
		glScaled(size, -size, 1);
		glRotated(-rotation, 0, 0, 1);
		if (centered)
			glTranslated(-font.getWidth(string)/2 , -font.getHeight(string)/2 , 0);
		
		TextureImpl.bindNone();
		Color fontColour = Colour.getCurrentColour();
		font.drawString(0, 0, string, fontColour);
		TextureImpl.unbind();
		glDisable(GL_TEXTURE_2D);
		glPopMatrix();
	}

	public static int[] getStringDimensions(String string) {
		int[] dimensions = new int[2];
		
		dimensions[0] = font.getWidth(string);
		dimensions[1] = font.getHeight(string);
		
		return dimensions;
	}
	public static void setWireFrame(boolean enabled) {
		isWireFrame = enabled;
	}
	
	public static Texture loadImage(String src, boolean smooth, int animatedImageCountX) {
		try {
			return TextureLoader.loadTextureFromFile(src, smooth, animatedImageCountX);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
	public static void drawImage(Texture image, double x, double y, double width, double height) {
		image.draw(x, y, width, height);
	}
	
	public static void reset() {
		setWireFrame(false);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
        Colour.useBackgroundColour();
	}

    public static void GLpush() {
        glPushMatrix();
    }
    public static void GLpop() {
        glPopMatrix();
    }
    public static void GLtranslate(double x, double y) {
        glTranslated(x, y, 0);
    }
    public static void GLrotate(double phi) {
        glRotated(phi, 0, 0, 1);
    }
    public static void GLscale(double sx, double sy) {
        glScaled(sx, sy, 1);
    }
}
