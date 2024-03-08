package uk.minersonline.SwingDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements KeyListener {
    private final Player player;
    private final List<Sprite> sprites;

    public Board() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.CYAN);

        player = new Player(640 / 2, 480 / 2);
        sprites = new ArrayList<>(List.of(player));
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
}
