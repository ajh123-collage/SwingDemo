package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;
import uk.minersonline.SwingDemo.sprites.Player;
import uk.minersonline.SwingDemo.sprites.Sprite;
import uk.minersonline.SwingDemo.sprites.missile.Missile;
import uk.minersonline.SwingDemo.sprites.missile.MissileSpawner;
import uk.minersonline.SwingDemo.utils.Drawable;
import uk.minersonline.SwingDemo.utils.Tickable;
import uk.minersonline.SwingDemo.utils.WorldTiles;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Board extends JPanel implements KeyListener, ActionListener {
    private final Player player;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;
    private final MissileSpawner missiles;
    private final BufferedImage water_background;
    private final BufferedImage grass_background;
    private final BufferedImage grass_beach_bottom_background;
    private final BufferedImage grass_beach_top_background;
    private final BufferedImage water_beach_bottom_background;
    private final BufferedImage water_beach_top_background;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.CYAN);

        try {
            this.water_background = ResourceManager.loadBufferedImage(WorldTiles.WATER_TILE);
            this.grass_background = ResourceManager.loadBufferedImage(WorldTiles.GRASS_TILE);
            this.grass_beach_bottom_background = ResourceManager.loadBufferedImage(WorldTiles.GRASS_BEACH_BOTTOM_TILE);
            this.grass_beach_top_background = ResourceManager.loadBufferedImage(WorldTiles.GRASS_BEACH_TOP_TILE);
            this.water_beach_bottom_background = ResourceManager.loadBufferedImage(WorldTiles.WATER_BEACH_BOTTOM_TILE);
            this.water_beach_top_background = ResourceManager.loadBufferedImage(WorldTiles.WATER_BEACH_TOP_TILE);
        } catch (ResourceLoadingException e) {
            throw new RuntimeException(e);
        }

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
            if (sprite instanceof Tickable tickable) {
                tickable.tick();
            }
        }

//        sprites.removeIf(sprite -> sprite.canRemove(this));
        //noinspection Java8CollectionRemoveIf - CollectionRemoveIf cuases ConccurntModifcationExpection
        for (Sprite sprite : sprites) {
            if (sprite.canRemove(this)) {
                sprites.remove(sprite);
            }
        }

        repaint();
    }

    public void removeMissiles() {
        sprites.removeIf(sprite -> sprite instanceof Missile);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        int width = (int) getBounds().getWidth();
        int height = (int) getBounds().getHeight();
        for (int x5 = 0; x5 < width; x5 += WorldTiles.TILE_WIDTH) {
            for (int y5 = 0; y5 < height; y5 += WorldTiles.TILE_HEIGHT) {
                if (y5 == 0 || (y5 >= (height - WorldTiles.TILE_HEIGHT))) {
                    graphics.drawImage(grass_background, x5, y5, this);
                } else if (y5 == WorldTiles.TILE_HEIGHT) {
                    graphics.drawImage(grass_beach_top_background, x5, y5, this);
                } else if (y5 == WorldTiles.TILE_HEIGHT * 2) {
                    graphics.drawImage(water_beach_top_background, x5, y5, this);
                } else if (y5 >= (height - (WorldTiles.TILE_HEIGHT * 3)) && y5 < (height - (WorldTiles.TILE_HEIGHT * 2))) {
                    graphics.drawImage(water_beach_bottom_background, x5, y5, this);
                } else if (y5 >= (height - (WorldTiles.TILE_HEIGHT * 2))) {
                    graphics.drawImage(grass_beach_bottom_background, x5, y5, this);
                } else {
                    graphics.drawImage(water_background, x5, y5, this);
                }
            }
        }

        for (Sprite sprite : sprites) {
            if (sprite instanceof Drawable drawable) {
                drawable.draw(graphics, this);
            }
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

    public Player getPlayer() {
        return player;
    }

    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }

    public MissileSpawner getMissiles() {
        return missiles;
    }
}
