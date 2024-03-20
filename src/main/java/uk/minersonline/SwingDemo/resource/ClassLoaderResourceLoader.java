package uk.minersonline.SwingDemo.resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import static uk.minersonline.SwingDemo.resource.ResourceManager.TEXTURE_PREFIX;

class ClassLoaderResourceLoader implements ResourceLoader {
	@Override
	public BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException {
		try {
			Path pathO = path.toPath(TEXTURE_PREFIX);
			InputStream file = this.getClass().getClassLoader().getResourceAsStream(pathO.toString());
			if (file == null) {
				throw new IOException("Could not load file, the file is not found");
			}
			return ImageIO.read(file);
		} catch (IOException e) {
			throw new ResourceLoadingException();
		}
	}

	@Override
	public ImageIcon loadImageIcon(ResourceIdentifier path) throws ResourceLoadingException {
		Path pathO = path.toPath(TEXTURE_PREFIX);
		URL fileURL = this.getClass().getClassLoader().getResource(pathO.toString());
		if (pathO.toFile().exists() && fileURL != null) {
			return new ImageIcon(fileURL);
		} else {
			throw new ResourceLoadingException("The file could not be found");
		}
	}
}
