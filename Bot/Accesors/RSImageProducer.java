package Bot.Accesors;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 11/12/2016.
 */
public interface RSImageProducer {
    public Image getImage();
    public BufferedImage getRenderedImage();
    public int getX();
    public int getY();
}
