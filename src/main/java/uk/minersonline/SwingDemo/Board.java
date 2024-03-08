package uk.minersonline.SwingDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements KeyListener, ActionListener {
    private final Player player;
    private final List<Sprite> sprites;

    public Board() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.CYAN);

        player = new Player(
                (640 / 2) - (Player.PLAYER_WIDTH / 2),
                (480 / 2) - (Player.PLAYER_HEIGHT / 2)
        );
        sprites = new ArrayList<>(List.of(player));

        new Timer(25, this).start();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }
    }
}
