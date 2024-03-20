package uk.minersonline.SwingDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Board extends JPanel implements KeyListener, ActionListener {
    private final Player player;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;

    public Board() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.CYAN);

        player = new Player(
                (640 / 2) - (PLAYER_WIDTH / 2),
                (480 / 2) - (PLAYER_HEIGHT / 2)
        );
        sprites = new ArrayList<>(List.of(player));

        activeKeyCodes = new HashSet<>();

        new Timer(TICK_DELAY, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        player.handleActiveKeys(activeKeyCodes);

        for (Sprite sprite : sprites) {
            sprite.tick();
        }

        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }
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
