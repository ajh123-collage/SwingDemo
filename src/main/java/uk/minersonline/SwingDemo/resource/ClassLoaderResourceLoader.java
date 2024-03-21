package uk.minersonline.SwingDemo.resource;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

class ClassLoaderResourceLoader implements ResourceLoader {
	@Override
	public BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException {
		try {
			Path pathO = path.toPath();
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
		Path pathO = path.toPath();
		URL url = this.getClass().getClassLoader().getResource(pathO.toString());
		if (url == null) {
			throw new ResourceLoadingException("The file url is null");
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}

	@Override
	public Clip loadClip(ResourceIdentifier path) throws ResourceLoadingException  {
		try {
			Path pathO = path.toPath();
			InputStream file = this.getClass().getClassLoader().getResourceAsStream(pathO.toString());
			if (file == null) {
				throw new NullPointerException("The file stream is null");
			}
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			return clip;
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			throw new ResourceLoadingException();
		}
    }
}
