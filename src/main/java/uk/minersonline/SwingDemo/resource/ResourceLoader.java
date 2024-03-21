package uk.minersonline.SwingDemo.resource;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.image.BufferedImage;

public interface ResourceLoader {
	BufferedImage loadBufferedImage(ResourceIdentifier path) throws ResourceLoadingException;
	ImageIcon loadImageIcon(ResourceIdentifier path) throws ResourceLoadingException;
	Clip loadClip(ResourceIdentifier path) throws ResourceLoadingException;
}
