package Bot.API;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 15/01/2017.
 */
public class Combat {

    private static Point MIDSCREEN = new Point(252, 180);
    /**
     * @return true if player is in combat.
     */
    public static boolean isInCombat(BufferedImage screen) {
        Color hpBar = new Color(0, 255, 0);
        java.util.List<Point> hpBarLoc = ImageUtil.getPointsWithColor(screen, hpBar, 0.1D);
        Point nearest = null;
        double dist = 0;
        double maxDist = 100;
        for (Point POINT : hpBarLoc) {
            double distTmp = Calc.getDistanceBetween(POINT, MIDSCREEN);
            if (distTmp < maxDist) {
                if (nearest == null) {
                    dist = distTmp;
                    nearest = POINT;
                } else if (distTmp < dist) {
                    nearest = POINT;
                    dist = distTmp;
                }
            }
        }
        return nearest != null;
    }
}
