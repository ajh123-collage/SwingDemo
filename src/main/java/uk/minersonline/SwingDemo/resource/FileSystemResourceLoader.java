package uk.minersonline.SwingDemo.resource;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

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

	@Override
	public Clip loadClip(ResourceIdentifier path) throws ResourceLoadingException {
		try {
			Path pathO = path.toPath();
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(pathO.toString()));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			return clip;
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			throw new ResourceLoadingException("There was an error whilst loading the clip", e);
		}
	}
}
