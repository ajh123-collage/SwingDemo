package uk.minersonline.SwingDemo.utils;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;

public final class Constants {
	private Constants() {
		// prevents instantiation
	}

	public static final int BOARD_WIDTH = WorldTiles.TILE_WIDTH * 9;
	public static final int BOARD_HEIGHT = WorldTiles.TILE_HEIGHT * 9;

	// Player
	public static final ResourceIdentifier PLAYER_IMAGE_PATH = new ResourceIdentifier("textures/character.png");
	public static final int PLAYER_WIDTH = 64;
	public static final int PLAYER_HEIGHT = 32;
	public static final int PLAYER_SPEED = 10;

	// Missiles
	public static final ResourceIdentifier MISSILE_IMAGE_PATH = new ResourceIdentifier("textures/missile.png");
	public static final int MISSILE_WIDTH = 32;
	public static final int MISSILE_HEIGHT = 16;
	public static final int MISSILE_SPEED = 1;
	public static final int MISSILE_DELAY = 500;

	// Explosion
	public static final ResourceIdentifier EXPLOSION_IMAGE_PATH = new ResourceIdentifier("textures/explosion.gif");
	public static final ResourceIdentifier EXPLOSION_SOUND_PATH = new ResourceIdentifier("sounds/explosion.wav");
	public static final int EXPLOSION_WIDTH = 64;
	public static final int EXPLOSION_HEIGHT = 64;
	public static final int EXPLOSION_DELAY = 160;

	// A delay of 25 milliseconds results in a frame rate of 45 FPS.
	public static final int TICK_DELAY = 25;

}