package Bot.API;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 25/11/2016.
 */
public class ColorUtil{

    public static void clickAtGameColor(Color c) {
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
    public static void clickAtGameColor(Color c, int t) {
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
}