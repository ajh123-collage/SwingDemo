package uk.minersonline.SwingDemo;

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

    private BufferedImage image;

    public Sprite(String imagePath, int x, int y, int w, int h) {
        this.position = new Point(x, y);
        this.size = new Dimension(w, h);
        loadImageResource(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            Path path = Paths.get("textures", imagePath);
            this.image = ImageIO.read(new File(path.toString()));
        } catch (IOException e) {
            System.err.println("An error occurred whilst loading the file [" + imagePath + "]");
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    private void loadImageResource(String imagePath) {
        try {
            Path path = Paths.get("textures", imagePath);
            InputStream file = this.getClass().getClassLoader().getResourceAsStream(path.toString());
            if (file == null) {
                throw new IOException("Could not load file, the file is null");
            }
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("An error occurred whilst loading the file [" + imagePath + "]");
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    public void draw(Graphics graphics, ImageObserver observer) {
        graphics.drawImage(this.image, position.x, position.y, size.width, size.height, observer);
    }
}
