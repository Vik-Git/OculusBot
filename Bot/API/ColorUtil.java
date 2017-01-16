package Bot.API;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 25/11/2016.
 */
public class ColorUtil{

    public static void clickAtGameColor(Color c) throws InterruptedException {
        BufferedImage current = Screen.getGameScreen();
        for (int i = 0; i < current.getWidth(); i++) {
            for (int j = 0; j < current.getHeight(); j++) {
                if (current.getRGB(i, j)==c.getRGB()) {
                Mouse.leftClick(i,j);
                }
                System.out.println(i+"---"+j);
            }
        }
    }

    public static Point getGameColorLocation(Color c,int t) throws InterruptedException {
        BufferedImage current = Screen.getGameScreen();
        Color tolerance = new Color(t, t, t);
        for (int i = 0; i < current.getWidth(); i++) {
            for (int j = 0; j < current.getHeight(); j++) {
                if(areColorsWithinTolerance(new Color(current.getRGB(i,j)),c,t)){
                    return  new Point(i,j);
                }
            }
        }
        return null;
    }

    public static void clickAtGameColor(Color c, int t) throws InterruptedException {
        BufferedImage current = Screen.getGameScreen();
        Color tolerance = new Color(t, t, t);
        for (int i = 0; i < current.getWidth(); i++) {
            for (int j = 0; j < current.getHeight(); j++) {
                if(areColorsWithinTolerance(new Color(current.getRGB(i,j)),c,t)){
                   Mouse.leftClick(i,j);
                }
            }
        }
    }
    public static boolean areColorsWithinTolerance(Color color1, Color color2,
                                                   int t) {
        Color tolerance = new Color(t, t, t);
        return (color1.getRed() - color2.getRed() < tolerance.getRed() && color1
                .getRed() - color2.getRed() > -tolerance.getRed())
                && (color1.getBlue() - color2.getBlue() < tolerance.getBlue() && color1
                .getBlue() - color2.getBlue() > -tolerance.getBlue())
                && (color1.getGreen() - color2.getGreen() < tolerance
                .getGreen() && color1.getGreen() - color2.getGreen() > -tolerance
                .getGreen());
    }

    public static double getDistance(double r1, double g1, double b1,
                                     double r2, double g2, double b2) {
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