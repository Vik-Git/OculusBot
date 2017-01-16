package Bot.API;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Vik on 15/01/2017.
 */
public class ImageUtil {

    public static Color[][] getColors(final BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        final Color[][] colors = new Color[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                colors[x][y] = getColorAt(image, x, y);
            }
        }
        return colors;
    }

    public static Color getColorAt(final BufferedImage image, final int x, final int y) {
        if (x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()) {
            return new Color(image.getRGB(x, y));
        }
        return Color.BLACK;
    }

    public static LinkedList<Point> getPointsWithColor(final BufferedImage image, final Rectangle bounds, final Color color, final double threshold) {
        final LinkedList<Point> points = new LinkedList<Point>();
        final Color[][] colors = getColors(image);
        for (int x = bounds.x; x < bounds.width; x++) {
            for (int y = bounds.y; y < bounds.height; y++) {
                if (ColorUtil.getDistance(colors[x][y], color) <= threshold) {
                    points.add(new Point(x, y));
                }
            }
        }
        return points;
    }


    public static LinkedList<Point> getPointsWithColor(final BufferedImage image, final Color color, final double threshold) {

        return getPointsWithColor(image, new Rectangle(image.getWidth(), image.getHeight()), color, threshold);
    }
}
