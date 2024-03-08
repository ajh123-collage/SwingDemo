package uk.minersonline.SwingDemo;

import uk.minersonline.SwingDemo.resource.ResourceLoadingException;
import uk.minersonline.SwingDemo.resource.ResourceManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sprite {
    protected Point position;
    protected Dimension size;

    private final BufferedImage image;

    public Sprite(String imagePath, int x, int y, int w, int h) {
		BufferedImage image;
		this.position = new Point(x, y);
        this.size = new Dimension(w, h);

        try {
            image = ResourceManager.loadImage(imagePath);
        } catch (ResourceLoadingException e) {
            image = null;
            System.err.println("An error occurred whilst loading the file [" + imagePath + "]");
            e.printStackTrace(System.err);
            System.exit(-1);
        }
		this.image = image;
	}

    public void draw(Graphics graphics, ImageObserver observer) {
        if (this.image != null) {
            graphics.drawImage(this.image, position.x, position.y, size.width, size.height, observer);
        }
    }
}
