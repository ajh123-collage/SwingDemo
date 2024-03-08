package uk.minersonline.SwingDemo.resource;

import java.awt.image.BufferedImage;

public interface ResourceLoader {
	BufferedImage loadImage(String path) throws ResourceLoadingException;
}
