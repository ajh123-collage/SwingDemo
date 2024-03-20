package uk.minersonline.SwingDemo.resource;

import java.awt.image.BufferedImage;

public interface ResourceLoader {
	BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException;
}
