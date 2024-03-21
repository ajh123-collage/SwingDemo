package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.utils.MathHelper;

import java.awt.event.KeyEvent;
import java.util.Set;
import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Player extends ImageSprite {
    private double dx;
    private double dy;

    private int score = 0;

    public Player(int x, int y) {
        super(PLAYER_IMAGE_PATH, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public void tick() {
        position.translate((int)dx, (int)dy);

        position.x = MathHelper.clamp(position.x, 0, BOARD_WIDTH - PLAYER_WIDTH);
        position.y = MathHelper.clamp(position.y, 0, BOARD_HEIGHT - PLAYER_HEIGHT);
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
}
