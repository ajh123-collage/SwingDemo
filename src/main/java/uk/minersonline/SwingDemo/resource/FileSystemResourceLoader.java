package uk.minersonline.SwingDemo.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static uk.minersonline.SwingDemo.resource.ResourceManager.TEXTURE_PREFIX;

public class FileSystemResourceLoader implements ResourceLoader {
	@Override
	public BufferedImage loadImage(String path) throws ResourceLoadingException {
		try {
			Path pathO = Paths.get(TEXTURE_PREFIX, path);
			return ImageIO.read(new File(pathO.toString()));
		} catch (IOException e) {
			throw new ResourceLoadingException();
		}
	}
}
