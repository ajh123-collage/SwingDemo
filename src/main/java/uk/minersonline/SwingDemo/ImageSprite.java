package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;
import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;
import uk.minersonline.SwingDemo.utils.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class ImageSprite extends Sprite implements Drawable {
    private final BufferedImage image;

    public ImageSprite(ResourceIdentifier imagePath, int x, int y, int w, int h) {
        super(x, y, w, h);
		BufferedImage image;

        try {
            image = ResourceManager.loadBufferedImage(imagePath);
        } catch (ResourceLoadingException e) {
            image = null;
            System.err.println("An error occurred whilst loading the file [" + imagePath + "]");
            e.printStackTrace(System.err);
            System.exit(-1);
        }
		this.image = image;
	}

    @Override
    public void draw(Graphics graphics, ImageObserver observer) {
        if (this.image != null) {
            graphics.drawImage(this.image, position.x, position.y, size.width, size.height, observer);
        }
    }
}
