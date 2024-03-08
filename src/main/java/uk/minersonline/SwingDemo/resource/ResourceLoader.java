package uk.minersonline.SwingDemo.resource;

import java.awt.image.BufferedImage;

public interface ResourceLoader {
	BufferedImage loadImage(ResourceIdentifier path) throws ResourceLoadingException;
}
