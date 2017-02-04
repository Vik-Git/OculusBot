package Bot.API;

import java.awt.*;

/**
 * Created by Vik on 14/01/2017.
 */
public class Calc {

    public static int getDistanceBetween(final Point p1, final Point p2) {
        if (p1 == null || p2 == null) {
            return -1;
        }
        final int xDiff = p2.x - p1.x;
        final int yDiff = p2.y - p1.y;
        return (int) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public static double getDistance(double r1, double g1, double b1, double r2, double g2, double b2) {
        double red = r2 - r1;
        double green = g2 - g1;
        double blue = b2 - b1;
        return Math.sqrt(red * red + green * green + blue * blue);
    }

    public static double getDistance(final Color c1, final Color c2) {
        float rgb1[] = new float[3];
        float rgb2[] = new float[3];
        c1.getColorComponents(rgb1);
        c2.getColorComponents(rgb2);
        return getDistance(rgb1[0], rgb1[1], rgb1[2], rgb2[0], rgb2[1], rgb2[2]);
    }
}
