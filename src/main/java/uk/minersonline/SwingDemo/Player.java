package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.utils.MathHelper;

import java.awt.event.KeyEvent;
import java.util.Set;
import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Player extends Sprite {
    double dx;
    double dy;

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

        if (activeKeyCodes.contains(KeyEvent.VK_W)) {
            dy -= PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_D)) {
            dx += PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_S)) {
            dy += PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_A)) {
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
}
