import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Main {
    public static void main(String[] args) {
        Image image = new Image("F:\\Users\\User\\Pictures\\123123123.png",80,80);

        // Get the image dimensions
        int width = image.getWidth();
        int height = image.getHeight();

        // Loop through each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR((pixel >> 16) & 0xff,(pixel >> 8) & 0xff,pixel & 0xff)));
                System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR((pixel >> 16) & 0xff,(pixel >> 8) & 0xff,pixel & 0xff)));
            }
            System.out.println();
        }
    }
}
