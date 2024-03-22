package uk.minersonline.SwingDemo.sprites;

import uk.minersonline.SwingDemo.resource.ResourceIdentifier;
import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;
import uk.minersonline.SwingDemo.utils.Drawable;

import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class ImageSprite extends Sprite implements Drawable {
    private final BufferedImage image;

    private ImageSprite(BufferedImage image, AffineTransformOp transform, int x, int y, int w, int h) {
        super(x, y, w, h);
        if (transform == null) {
            this.image = image;
        } else {
            this.image = transform.filter(image, null);
        }
    }

    public ImageSprite(ResourceIdentifier imagePath, int x, int y, int w, int h) {
        this(loadImage(imagePath), null, x, y, w, h);
	}

    public ImageSprite(ResourceIdentifier imagePath, int x, int y, int w, int h, AffineTransformOp transform) {
        this(loadImage(imagePath), transform, x, y, w, h);
    }

    @Override
    public void draw(Graphics graphics, ImageObserver observer) {
        if (this.image != null) {
            graphics.drawImage(this.image, position.x, position.y, size.width, size.height, observer);
        }
    }

    private static BufferedImage loadImage(ResourceIdentifier imagePath) {
		try {
			return ResourceManager.loadBufferedImage(imagePath);
		} catch (ResourceLoadingException e) {
			throw new RuntimeException(e);
		}
	}
}
