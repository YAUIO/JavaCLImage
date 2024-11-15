import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    public BufferedImage img;

    private static BufferedImage rescaleImg(BufferedImage img, int width, int height){
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
        Double multiplier = null;
        if (img.getWidth() > width || img.getHeight() > height) {
            if (width / img.getWidth() < height / img.getHeight()) {
                multiplier = (double) width / img.getWidth();
            } else {
                multiplier = (double) height / img.getHeight();
            }
            if (multiplier-0.04>0){
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
            if (multiplier-0.1>0){
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

    Image(String path, int x, int y){
       try {
            img = ImageIO.read(new File(path));
            img = rescaleImg(img,x,y);
        } catch (IOException e) {

        }
    }

    public int getRGB(int x, int y){
        return img.getRGB(x,y);
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }
}
