package uk.minersonline.SwingDemo.utils;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;

public final class WorldTiles {
    private WorldTiles() {
        // prevents instantiation
    }

    public static final ResourceIdentifier GRASS_TILE = new ResourceIdentifier("textures/terrain/grass0/straight/0/0.png");
    public static final ResourceIdentifier GRASS_BEACH_BOTTOM_TILE = new ResourceIdentifier("textures/terrain/grass-beach0/straight/270/0.png");
    public static final ResourceIdentifier GRASS_BEACH_TOP_TILE = new ResourceIdentifier("textures/terrain/grass-beach0/straight/90/0.png");
    public static final ResourceIdentifier WATER_BEACH_BOTTOM_TILE = new ResourceIdentifier("textures/terrain/beach-shallow0/straight/270/0.png");
    public static final ResourceIdentifier WATER_BEACH_TOP_TILE = new ResourceIdentifier("textures/terrain/beach-shallow0/straight/90/0.png");
    public static final ResourceIdentifier WATER_TILE = new ResourceIdentifier("textures/terrain/shallow0/straight/0/0.png");
}
