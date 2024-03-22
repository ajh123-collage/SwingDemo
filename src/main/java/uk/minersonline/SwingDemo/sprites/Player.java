package uk.minersonline.SwingDemo.sprites;

import uk.minersonline.SwingDemo.Board;
import uk.minersonline.SwingDemo.utils.Tickable;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.Set;
import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Player extends ImageSprite implements Tickable {
    private double dx;
    private double dy;

    private int score = 0;
    private int health = 1;

    public Player(int x, int y) {
        super(PLAYER_IMAGE_PATH, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, getTransform());
    }

    @Override
    public void tick() {
        position.translate((int)dx, (int)dy);

        position.x = Math.clamp(position.x, 0, BOARD_WIDTH - PLAYER_WIDTH);
        position.y = Math.clamp(position.y, 0, BOARD_HEIGHT - PLAYER_HEIGHT);
    }

    public void handleActiveKeys(Set<Integer> activeKeyCodes) {
        dx = 0;
        dy = 0;

        if (activeKeyCodes.contains(KeyEvent.VK_UP)) {
            dy -= PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_RIGHT)) {
            dx += PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_DOWN)) {
            dy += PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_LEFT)) {
            dx -= PLAYER_SPEED;
        }

        normalizeDeltas();
    }

    private void normalizeDeltas() {
        if (dx != 0 && dy != 0) {
            dx /= Math.sqrt(2);
            dy /= Math.sqrt(2);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean canRemove(Board board) {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private static AffineTransformOp getTransform() {
        AffineTransform rotation = AffineTransform.getQuadrantRotateInstance(1, PLAYER_WIDTH, PLAYER_HEIGHT);
		return new AffineTransformOp(rotation, AffineTransformOp.TYPE_BILINEAR);
    }
}
