package uk.minersonline.SwingDemo;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Board extends JPanel implements KeyListener, ActionListener {
    private final Player player;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;
    private final MissileSpawner missiles;

    public Board() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.CYAN);

        player = new Player(
                (640 / 2) - (PLAYER_WIDTH / 2),
                (480 / 2) - (PLAYER_HEIGHT / 2)
        );
        sprites = new CopyOnWriteArrayList<>();
        sprites.add(player);

        activeKeyCodes = new HashSet<>();

        new Timer(TICK_DELAY, this).start();
        this.missiles = new MissileSpawner(sprites);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        player.handleActiveKeys(activeKeyCodes);

        for (Sprite sprite : sprites) {
            sprite.tick();

            if (sprite instanceof Missile) {
                Missile missile = (Missile) sprite;

                if (missile.isColliding(player)) {
                    removeMissiles();
                    sprites.add(new Explosion(missile.position.x, missile.position.y));
                    sprites.remove(player);
                    missiles.stop();
                } else if (missile.position.x < 0) {
                    sprites.remove(missile);
                    player.setScore(player.getScore() + 1);
                }
            }

            if (sprite instanceof Explosion) {
                Explosion explosion = (Explosion) sprite;
                if (explosion.getCoolDown() <= 0) {
                    sprites.remove(explosion);
                }
            }
        }

        repaint();
    }

    private void removeMissiles() {
        sprites.removeIf(sprite -> sprite instanceof Missile);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }
        String score = String.valueOf(player.getScore());
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        graphics.drawString(score, BOARD_WIDTH - (score.length() * 32), 32);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        activeKeyCodes.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        activeKeyCodes.remove(keyEvent.getKeyCode());
    }
}
