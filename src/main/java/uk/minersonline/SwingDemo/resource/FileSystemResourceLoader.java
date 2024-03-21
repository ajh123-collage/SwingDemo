package uk.minersonline.SwingDemo.resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static uk.minersonline.SwingDemo.resource.ResourceManager.TEXTURE_PREFIX;

class FileSystemResourceLoader implements ResourceLoader {
	@Override
	public BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException {
		try {
			Path pathO = path.toPath();
			return ImageIO.read(new File(pathO.toString()));
		} catch (IOException e) {
			throw new ResourceLoadingException("The file could not be found", e);
		}
	}

	@Override
	public ImageIcon loadImageIcon(ResourceIdentifier path) throws ResourceLoadingException {
		Path pathO = path.toPath();
		if (pathO.toFile().exists()) {
			return new ImageIcon(pathO.toString());
		} else {
			throw new ResourceLoadingException("The file could not be found");
		}
	}
}
