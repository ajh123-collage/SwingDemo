package uk.minersonline.SwingDemo.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static uk.minersonline.SwingDemo.resource.ResourceManager.TEXTURE_PREFIX;

class FileSystemResourceLoader implements ResourceLoader {
	@Override
	public BufferedImage loadImage(ResourceIdentifier path) throws ResourceLoadingException {
		try {
			Path pathO = path.toPath(TEXTURE_PREFIX);
			return ImageIO.read(new File(pathO.toString()));
		} catch (IOException e) {
			throw new ResourceLoadingException("The file could not be found", e);
		}
	}
}
