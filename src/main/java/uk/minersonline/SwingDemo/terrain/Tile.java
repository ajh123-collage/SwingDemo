package uk.minersonline.SwingDemo.terrain;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;
import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;

import java.awt.image.BufferedImage;

public enum Tile {
	GRASS_TILE(new ResourceIdentifier("textures/terrain/grass0/straight/0/0.png")),
	GRASS_BEACH_BOTTOM(new ResourceIdentifier("textures/terrain/grass-beach0/straight/270/0.png")),
	GRASS_BEACH_TOP(new ResourceIdentifier("textures/terrain/grass-beach0/straight/90/0.png")),
	WATER_BEACH_BOTTOM(new ResourceIdentifier("textures/terrain/beach-shallow0/straight/270/0.png")),
	WATER_BEACH_TOP(new ResourceIdentifier("textures/terrain/beach-shallow0/straight/90/0.png")),
	WATER(new ResourceIdentifier("textures/terrain/shallow0/straight/0/0.png"));

	private final BufferedImage image;

	Tile(ResourceIdentifier image) {
		try {
			this.image = ResourceManager.loadBufferedImage(image);
		} catch (ResourceLoadingException e) {
			throw new RuntimeException(e);
		}
	}

	public BufferedImage getImage() {
		return image;
	}
}
