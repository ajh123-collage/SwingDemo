package uk.minersonline.SwingDemo;

import java.awt.*;

public abstract class Sprite {
    protected Point position;
    protected Dimension size;

    public Sprite(int x, int y, int w, int h) {
		this.position = new Point(x, y);
        this.size = new Dimension(w, h);
	}

    public Point getPosition() {
        return position;
    }

    public Dimension getSize() {
        return size;
    }

    public abstract void tick();

    public Point getTopLeft() {
        return position;
    }

    public Point getBottomRight() {
        return new Point(position.x + size.width, position.y + size.height);
    }

    public boolean isColliding(Sprite other) {
        return this != other
                && this.getTopLeft().x < other.getBottomRight().x
                && this.getBottomRight().x > other.getTopLeft().x
                && this.getTopLeft().y < other.getBottomRight().y
                && this.getBottomRight().y > other.getTopLeft().y;
    }
}
