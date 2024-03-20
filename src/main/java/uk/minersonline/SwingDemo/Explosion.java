package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.utils.MathHelper;

import java.awt.*;
import java.awt.image.ImageObserver;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Explosion extends Sprite {
    private double coolDown;

    public Explosion(int x, int y) {
        super(EXPLOSION_IMAGE_PATH, x, y, EXPLOSION_WIDTH, EXPLOSION_HEIGHT);
        this.coolDown = 1;
    }

    @Override
    public void tick() {
        if (this.coolDown > 0) {
            this.coolDown -= 0.1;
        }
    }

    public double getCoolDown() {
        return coolDown;
    }

    @Override
    public void draw(Graphics graphics, ImageObserver observer) {
        super.draw(graphics, observer);
    }
}
