package Bot.API;

import java.awt.*;

/**
 * Created by Vik on 22/01/2017.
 */
public class Walking {
    public static final Rectangle COMPASS = new Rectangle(521, 3, 44, 42);
    public static Point PLAYER_MARKER =new Point(627, 85);

    public static void setCompassNorth() {
        Mouse.leftClick(COMPASS.x +(COMPASS.width / 2),COMPASS.y +(COMPASS.height/2));
        Util.sleep(600);
    }

    public static void walk(int x,int y){
        setCompassNorth();
        Mouse.leftClick(PLAYER_MARKER.x+(x*8),PLAYER_MARKER.y +(y*8));
    }
}
