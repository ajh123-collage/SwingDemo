package uk.minersonline.SwingDemo.sprites.missile;

import uk.minersonline.SwingDemo.sprites.Sprite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class MissileSpawner implements ActionListener {
    private final List<Sprite> sprites;
    private final Random random = new Random();
    private final Timer timer;

    public MissileSpawner(List<Sprite> sprites) {
        this.sprites = sprites;
        this.timer = new Timer(MISSILE_DELAY, this);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int _c = 0; _c < 1; _c ++) {
            int y = random.nextInt(BOARD_HEIGHT + 1);
            int x = random.nextInt((3 * MISSILE_WIDTH) - 1 + 1) + 1;
            sprites.add(new Missile((BOARD_WIDTH * 2) + x, y));
        }
    }

    public void stop() {
        this.timer.stop();
    }

    public void start() {
        this.timer.start();
    }
}
