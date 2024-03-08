package uk.minersonline.SwingDemo;

public class Player extends Sprite {
    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 64;
    public Player(int x, int y) {
        super("character.png", x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }
}
