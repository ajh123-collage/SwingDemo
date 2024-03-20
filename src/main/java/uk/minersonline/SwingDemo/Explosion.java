package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;
import uk.minersonline.SwingDemo.utils.MathHelper;

import java.awt.*;
import java.awt.image.ImageObserver;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Explosion extends Sprite {
    private double coolDown;
    private final Image icon;

    public Explosion(int x, int y) {
        super(EXPLOSION_IMAGE_PATH, x, y, EXPLOSION_WIDTH, EXPLOSION_HEIGHT);
        this.coolDown = 1;
        try {
            this.icon = ResourceManager.loadImageIcon(EXPLOSION_IMAGE_PATH).getImage();
        } catch (ResourceLoadingException e) {
            throw new RuntimeException(e);
        }
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
        if (this.icon != null) {
            graphics.drawImage(this.icon, position.x, position.y, size.width, size.height, observer);
        }
    }
}
