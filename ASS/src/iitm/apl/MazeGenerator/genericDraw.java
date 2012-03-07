package iitm.apl.MazeGenerator;

import java.awt.Graphics;

/**
 *
 * @author Sathyanarayanan S
 */
public abstract class genericDraw {
    protected int x, y;

    genericDraw() {
        x = 0;
        y = 0;
    }

    void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    abstract void draw(Graphics g, String str);
}
