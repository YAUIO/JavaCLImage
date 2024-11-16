import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CLImage {
    private BufferedImage origimg;
    private BufferedImage img;
    private int lastX;
    private int lastY;

    private static BufferedImage rescaleImg(BufferedImage img, int width, int height) {
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
        Double multiplier = null;
        if (img.getWidth() > width || img.getHeight() > height) {
            if (width / img.getWidth() < height / img.getHeight()) {
                multiplier = (double) width / img.getWidth();
            } else {
                multiplier = (double) height / img.getHeight();
            }
            if (multiplier - 0.04 > 0) {
                multiplier -= 0.04;
            }
            newWidth = (int) (newWidth * multiplier);
            newHeight = (int) (newHeight * multiplier);
        } else if (img.getWidth() < width || img.getHeight() < height) {
            if (width / img.getWidth() < height / img.getHeight()) {
                multiplier = (double) width / img.getWidth();
            } else {
                multiplier = (double) height / img.getHeight();
            }
            if (multiplier - 0.1 > 0) {
                multiplier -= 0.1;
            }
            newWidth = (int) (newWidth * multiplier);
            newHeight = (int) (newHeight * multiplier);
        }

        BufferedImage out = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D buf = out.createGraphics();
        buf.drawImage(img, 0, 0, newWidth, newHeight, null);
        buf.dispose();
        return out;
    }

    CLImage(String path, int x, int y) throws IOException {
        origimg = ImageIO.read(new File(path));
        img = rescaleImg(origimg, x, y);
        lastX = x;
        lastY = y;
    }

    CLImage(String path) throws IOException {
        origimg = ImageIO.read(new File(path));
        img = origimg;
    }

    public void print(int x, int y) {
        if (x != lastX && y != lastY) {
            img = rescaleImg(origimg, x, y);
            lastX = x;
            lastY = y;
        }
        print();
    }

    public void print() {
        // Get the image dimensions
        int width = img.getWidth();
        int height = img.getHeight();

        // Loop through each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = img.getRGB(x, y);
                System.out.print(Ansi.colorize("   ", Attribute.BACK_COLOR((pixel >> 16) & 0xff, (pixel >> 8) & 0xff, pixel & 0xff)));
            }
            System.out.println();
        }
    }
}
