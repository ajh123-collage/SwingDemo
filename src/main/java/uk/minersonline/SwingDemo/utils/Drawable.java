package uk.minersonline.SwingDemo.utils;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface Drawable {
    void draw(Graphics graphics, ImageObserver observer);
}
