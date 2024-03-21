package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Explosion extends Sprite implements ActionListener {
    private int coolDown;
    private final Image icon;

    public Explosion(int x, int y) {
        super(x, y, EXPLOSION_WIDTH, EXPLOSION_HEIGHT);
        this.coolDown = 6;
        try {
            this.icon = ResourceManager.loadImageIcon(EXPLOSION_IMAGE_PATH).getImage();
        } catch (ResourceLoadingException e) {
            throw new RuntimeException(e);
        }
        new Timer(EXPLOSION_DELAY, this).start();
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

    @Override
    public void tick() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.coolDown > 0) {
            this.coolDown -= 1;
        }
    }
}
