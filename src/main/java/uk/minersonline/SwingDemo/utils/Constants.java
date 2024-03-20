package uk.minersonline.SwingDemo.utils;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;

public final class Constants {
	private Constants() {
		// prevents instantiation
	}

	public static final int BOARD_WIDTH = 640;
	public static final int BOARD_HEIGHT = 480;

	public static final ResourceIdentifier PLAYER_IMAGE_PATH = new ResourceIdentifier("character.png");
	public static final ResourceIdentifier MISSILE_IMAGE_PATH = new ResourceIdentifier("missile.png");
	public static final int PLAYER_WIDTH = 64;
	public static final int PLAYER_HEIGHT = 32;
	public static final int MISSILE_WIDTH = 32;
	public static final int MISSILE_HEIGHT = 16;
	public static final int PLAYER_SPEED = 10;
	public static final int MISSILE_SPEED = 1;

	// A delay of 25 milliseconds results in a frame rate of 45 FPS.
	public static final int TICK_DELAY = 25;
	public static final int MISSILE_DELAY = 500;
}