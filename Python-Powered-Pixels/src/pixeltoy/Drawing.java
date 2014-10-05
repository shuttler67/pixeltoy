package pixeltoy;

import graphics.Colour;
import graphics.GraphicsController;

public class Drawing {
	public void drawRectangle(double x, double y, double width, double height, double rotation) {
		GraphicsController.drawRectangle(x, y, width, height, rotation);
	}
	public void drawRectangle(double x, double y, double width, double height) {
		GraphicsController.drawRectangle(x, y, width, height, 0);
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
		GraphicsController.drawLine(x1, y1, x2, y2);
	}
	
	public void drawPoint(double x, double y) {
		GraphicsController.drawPoint(x, y);
	}
	
	public void drawString(double x, double y, String string, double size, boolean centered, double rotation) {
		drawString(x, y, string, size, rotation, centered);
	}
	public void drawString(double x, double y, String string, double size, double rotation, boolean centered) {
		GraphicsController.drawString(x, y, string, size, rotation, centered);
	}
	public void drawString(double x, double y, String string, double size, double rotation) {
		drawString(x, y, string, size, true, rotation);
	}
	public void drawString(double x, double y, String string, double size, boolean centered) {
		drawString(x, y, string, size, centered, 0);
	}
	public void drawString(double x, double y, String string, double size) {
		drawString(x, y, string, size, 0);
	}
	public void drawString(double x, double y, String string) {
		drawString(x, y, string, 1);
	}
	
	public int[] getStringDimensions(String string) {
		return GraphicsController.getStringDimensions(string);
	}
	
	public void drawCircle(double x, double y, double radius) {
		GraphicsController.drawCircle(x, y, radius);
	}
    public void drawCircle(double x, double y, double radius, double rotation) {
		GraphicsController.drawCircle(x, y, radius);
	}
	
	public void drawEllipse(double x, double y, double radiusX, double radiusY, double rotation) {
		GraphicsController.drawEllipse(x, y, radiusX, radiusY, rotation);
	}
	public void drawEllipse(double x, double y, double radiusX, double radiusY) {
		drawEllipse(x, y, radiusX, radiusY, 0);
	}
	
	public void drawPolygon(double[] points, double rotation) throws Exception {
        GraphicsController.drawPolygon(points, rotation);
	}

	public void drawPolygon(double[] points) throws Exception {
		drawPolygon(points, 0);
	}
	
	public void drawRegularPolygon(double x, double y, int order, double size, double rotation) {
		GraphicsController.drawRegularPolygon(x, y, order, size, rotation);
	}
	public void drawRegularPolygon(double x, double y, int order, double size) {
		drawRegularPolygon(x, y, order, size, 0);
	}
	
	public void useColour(double r, double g, double b, double a) {
		Colour.useColour(r, g, b, a);
	}
	
	public void useColour(double r, double g, double b) {
		Colour.useColour(r, g, b, 255);
	}
	
	public void setWireFrameMode(boolean enabled) {
		GraphicsController.setWireFrame(enabled);
	}

    public void GLpush() {
        GraphicsController.GLpush();
    }
    public void GLpop() {
        GraphicsController.GLpop();
    }
    public void GLtranslate( double dx, double dy ) {
        GraphicsController.GLtranslate(dx, dy);
    }
    public void GLrotate( double angle ) {
        GraphicsController.GLrotate( angle);
    }
    public void GLscale( double sx, double sy ) {
        GraphicsController.GLscale( sx, sy);
    }
}
