package Bot.API;

/**
 * Created by Vik on 23/01/2017.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
/**
 * Basic game image methods.
 *
 * @Author Vik
 */
public class Game {

    public static final Color ENERGY_ENABLED = new Color(252, 219, 1);
    public static final Rectangle ENERGY = new Rectangle(705, 93, 34, 33);
    public static final Rectangle VIEWPORT = new Rectangle(4, 4, 511, 334);

    public static final Rectangle PRAYER_TEXT_BOUNDS = new Rectangle(733, 58,
            28, 26);
    public static final Rectangle HP_TEXT_BOUNDS = new Rectangle(719, 19, 27,
            25);

    public static boolean isLoggedIn() {
        return ColorUtil.getColor(345, 400).equals(new Color(211, 194, 161));
    }

    public static boolean isLogOutButtonVisible() {
        return ColorUtil.getColor(693, 360).equals(new Color(116, 28, 26));
    }

    /**
     * Gets the current prayer amount
     *
     * @return The current prayer amount
     */
    public static int getCurrentPrayer() {
        String s = RSText.findString(PRAYER_TEXT_BOUNDS, null, null);
        if (s != null) {
            return Integer.parseInt(s);
        }
        return -1;
    }

    /**
     * gets the runescape view from the gameframe.
     *
     * @return
     */
    public static BufferedImage getViewImage() {
        return ColorUtil.getScreenPart(VIEWPORT);
    }

    /**
     * Gets the current hp amount
     *
     * @return The current amount
     */
    public static int getCurrentHP() {
        String s = RSText.findString(HP_TEXT_BOUNDS, null, null);
        if (s != null) {
            return Integer.parseInt(s);
        }
        return-1;
    }

    /**
     * Gets the game image.
     *
     * @return The game image.
     */
    public static BufferedImage getImage() {
        return Screen.getCompleteGameScreen();
    }

    /**
     * Gets the color at a given x-y coordinate on the game image.
     *
     * @param x
     *            The x coordinate.
     * @param y
     *            The y coordinate.
     * @return The color at the given coordinates.
     */
    public static Color getColorAt(final int x, final int y) {
        return ImageUtil.getColorAt(getImage(), x, y);
    }

    /**
     * Gets the color at a given point on the game image.
     *
     * @param p
     *            The point
     * @return The color at the given point.
     */
    public static Color getColorAt(final Point p) {
        return getColorAt(p.x, p.y);
    }

    /**
     * Gets the array of all colors of the game image.
     *
     * @return A two-dimensional array of the colors of the game image.
     */
    public static Color[][] getColors() {
        return ImageUtil.getColors(getImage());
    }

    /**
     * Gets all points within the game image which have a color within threshold
     * distance of a given color.
     *
     * @param color
     *            The color to scan for.
     * @param threshold
     *            The threshold to scan by.
     * @return A list of points where the color of the game image is within the
     *         threshold.
     */
    public static List<Point> getPointsWithColor(final Color color,
                                                 final double threshold) {
        return ImageUtil.getPointsWithColor(Game.getImage(), color, threshold);
    }

    /**
     * Gets all points within the game image which have a color equal to a given
     * color.
     *
     * @param color
     *            The color to scan for.
     * @return A list of points where the color of the game image is equal.
     */
    public static List<Point> getPointsWithColor(final Color color) {
        return getPointsWithColor(color, 0.0);
    }


    /**
     * Gets the center of the energy toggle button.
     *
     * @return The energy button's center.
     */
    public static Point getEnergyButtonCenter() {
        return new Point((int) (ENERGY.x + (ENERGY.width / 2)),
                (int) (ENERGY.y + (ENERGY.height / 2)));
    }

    public static int getEnergy() {
        String s = RSText.findString(ENERGY,null,null);
        if (s != null) {
            return Integer.parseInt(s);
        }
        return -1;
    }

    /**
     * Gets the color array of the energy button's display.
     *
     * @return The energy button's colors.
     */
    public static Color[][] getEnergyButtonColors() {
        final Color[][] colors = new Color[ENERGY.width][ENERGY.height];
        for (int x = ENERGY.x; x < ENERGY.x + ENERGY.width; x++) {
            for (int y = ENERGY.y; y < ENERGY.y + ENERGY.height; y++) {
                colors[x][y] = Game.getColors()[x][y];
            }
        }
        return colors;
    }

    /**
     * Checks if run is enabled.
     *
     * @return <tt>true</tt> if enabled; otherwise <tt>false</tt>.
     */
    public static boolean isRunEnabled() {
        for (final Color[] carr : getEnergyButtonColors()) {
            for (final Color c : carr) {
                if (c.equals(ENERGY_ENABLED)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Experience button.
     */
    public static class Experience {

        public static final Rectangle BOUNDS = new Rectangle(517, 47, 33, 33);
        public static final Rectangle DISPLAY_BOUNDS = new Rectangle(425, 53,
                88, 18);
        public static final Color[] TOGGLE_COLORS = { new Color(255, 255, 255),
                new Color(77, 73, 62), new Color(66, 65, 58) };

        /**
         * Gets the center of the experience button.
         *
         * @return The experience button's center.
         */
        public static Point getCenter() {
            return new Point((int) (BOUNDS.x + (BOUNDS.width / 2)),
                    (int) (BOUNDS.y + (BOUNDS.height / 2)));
        }

        /**
         * Gets the color array of the experience button.
         *
         * @return The experience button's colors.
         */
        public static Color[][] getColors() {
            final Color[][] colors = new Color[BOUNDS.width][BOUNDS.height];
            for (int x = BOUNDS.x; x < BOUNDS.x + BOUNDS.width; x++) {
                for (int y = BOUNDS.y; y < BOUNDS.y + BOUNDS.height; y++) {
                    colors[x][y] = Game.getColors()[x][y];
                }
            }
            return colors;
        }
    }
}
