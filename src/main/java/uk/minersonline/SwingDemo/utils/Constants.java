package uk.minersonline.SwingDemo.utils;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;

public final class Constants {
	private Constants() {
		// prevents instantiation
	}

	public static final int BOARD_WIDTH = 640;
	public static final int BOARD_HEIGHT = 480;

	public static final ResourceIdentifier PLAYER_IMAGE_PATH = new ResourceIdentifier("character.png");
	public static final int PLAYER_WIDTH = 32;
	public static final int PLAYER_HEIGHT = 64;
	public static final int PLAYER_SPEED = 10;

	// A delay of 25 milliseconds results in a frame rate of 45 FPS.
	public static final int TICK_DELAY = 25;
}