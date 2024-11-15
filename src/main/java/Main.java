import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CLImage image = new CLImage("F:\\Users\\User\\Pictures\\123123123.png");
        image.print(80,80);
    }
}
