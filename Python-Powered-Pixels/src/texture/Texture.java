package texture;

import static org.lwjgl.opengl.GL11.*;
import graphics.Colour;

public class Texture {
    private final int id;
    private final int width;
    private final int height;
    private final int animatedImageCountX;

    private double rotation = 0;
    private int animationFrame = 0;
    private int displayCallLists;

    public Texture(int id, int animatedImageCountX) {
        this.id = id;
        this.animatedImageCountX = animatedImageCountX;
        createDisplayCallLists();

        width = glGetTexParameteri(id, GL_TEXTURE_WIDTH);
        height = glGetTexParameteri(id, GL_TEXTURE_HEIGHT);
    }

    public void createDisplayCallLists() {
        displayCallLists = glGenLists(animatedImageCountX);

        double animationFrameSize = 1d / (double) animatedImageCountX;

        for (int i = 0; i < animatedImageCountX; i++) {
            double texCoordBase = ((double) i) * animationFrameSize;

            glNewList(displayCallLists + i, GL_COMPILE);

            glBegin(GL_QUADS);
            glTexCoord2d(texCoordBase, 0);
            glVertex2d(0, 0);
            glTexCoord2d(texCoordBase + animationFrameSize, 0);
            glVertex2d(width, 0);
            glTexCoord2d(texCoordBase + animationFrameSize, 1);
            glVertex2d(width, height);
            glTexCoord2d(texCoordBase, 1);
            glVertex2d(0, height);
            glEnd();

            glEndList();
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void setRotation(double rotationDegrees) {
        this.rotation = rotationDegrees;
    }

    public void rotate(double rotationDegrees) {
        this.rotation += rotationDegrees;
    }

    public void nextAnimationFrame() {
        animationFrame++;
        animationFrame = animationFrame % animatedImageCountX;
    }

    public void resetAnimation() {
        animationFrame = 0;
    }

    public void draw(double x, double y, double width, double height) {
        glEnable(GL_TEXTURE_2D);
        glColor4d(1, 1, 1, 1);

        this.bind();

        glPushMatrix();
        glTranslated(x, y, 0);
        glRotated(rotation, 0, 0, 1);
        glTranslated((-width) / 2d, (-height) / 2d, 0);

        glCallList(displayCallLists + animationFrame);

        glPopMatrix();

        glDisable(GL_TEXTURE_2D);
        Colour.useCurrentSelectedColour();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
