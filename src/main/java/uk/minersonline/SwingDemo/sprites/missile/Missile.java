package uk.minersonline.SwingDemo.sprites.missile;

import uk.minersonline.SwingDemo.sprites.ImageSprite;
import uk.minersonline.SwingDemo.utils.Tickable;

import static uk.minersonline.SwingDemo.utils.Constants.*;

public class Missile extends ImageSprite implements Tickable {
    private double dx;
    private double dy;

    public Missile(int x, int y) {
        super(MISSILE_IMAGE_PATH, x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

    @Override
    public void tick() {
        position.translate((int)dx, (int)dy);

        position.y = Math.clamp(position.y, 0, BOARD_HEIGHT - MISSILE_HEIGHT);

        dx -= MISSILE_SPEED;
    }
}