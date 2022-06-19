import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SingleImage
{
    public static void main(String[] args) throws IOException
    {
            BufferedImage originalImg = ImageIO.read(new File("./src/resources/savyx.png"));
            BufferedImage targetImg = new BufferedImage(originalImg.getWidth(), originalImg.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int x = 0; x < originalImg.getWidth(); x++)
            {
                for (int y = 0; y < originalImg.getHeight(); y++)
                {
                    int rgb = originalImg.getRGB(x, y);
//                    int red = (rgb & 0x00FF0000) >> 16; // 2 bytes
//                    int green = (rgb & 0x0000FF00) >> 8; // 1 byte
//                    int blue = (rgb & 0x000000FF);
//                    int transparency = (rgb & 0xFF000000) >> 24;

                    if((rgb & 0x00FF0000) >> 16 == 255 && (rgb & 0x0000FF00) >> 8 == 255 && (rgb & 0x000000FF) == 255)
                    {
                        int newRgb = 0x00FFFFFF;
                        targetImg.getRaster().setDataElements(x, y, targetImg.getColorModel().getDataElements(newRgb, null));
                    }
                    else
                    {
                        targetImg.getRaster().setDataElements(x, y, targetImg.getColorModel().getDataElements(rgb, null));
                    }
                }
            }
            File outputFile = new File("./out/savyx.png");
            ImageIO.write(targetImg, "png", outputFile);
    }
}
