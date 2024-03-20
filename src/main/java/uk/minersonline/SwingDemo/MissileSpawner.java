package uk.minersonline.SwingDemo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class MissileSpawner implements ActionListener {
    private final List<Sprite> sprites;
    private final Player player;
    private final Random random = new Random();
    private final Timer timer;

    public MissileSpawner(List<Sprite> sprites, Player player) {
        this.sprites = sprites;
        this.player = player;
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

        for (Sprite sprite : sprites) {
            if (sprite instanceof Missile) {
                Missile missile = (Missile) sprite;

                if (missile.isColliding(player)) {
                    sprites.remove(player);
                    this.timer.stop();
                } else if (missile.position.x < 0) {
                    sprites.remove(missile);
                    player.setScore(player.getScore() + 1);
                }
            }
        }
    }
}
