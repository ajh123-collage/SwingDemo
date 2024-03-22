package uk.minersonline.SwingDemo.sprites;

import uk.minersonline.SwingDemo.Board;
import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;
import uk.minersonline.SwingDemo.utils.Drawable;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Explosion extends Sprite implements ActionListener, Drawable {
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

        try {
            Clip sound = ResourceManager.loadClip(EXPLOSION_SOUND_PATH);
            sound.start();
        } catch (ResourceLoadingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Graphics graphics, ImageObserver observer) {
        if (this.icon != null) {
            graphics.drawImage(this.icon, position.x, position.y, size.width, size.height, observer);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.coolDown > 0) {
            this.coolDown -= 1;
        }
    }

    @Override
    public boolean canRemove(Board board) {
        return this.coolDown == 0;
    }
}
