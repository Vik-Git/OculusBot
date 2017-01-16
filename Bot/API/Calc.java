package Bot.API;

import java.awt.*;

/**
 * Created by Vik on 14/01/2017.
 */
public class Calc {

    public static Point distanceTo(Point source,Point target){
        Point result = new Point(source.x-target.x,source.y-target.y);

        if(source.x < 0){
            source.x = source.x*-1;
        }
        if(source.y < 0){
            source.y = source.y*-1;
        }
        return result;
    }

    public static int getDistanceBetween(final Point p1, final Point p2) {
        if (p1 == null || p2 == null) {
            return -1;
        }
        final int xDiff = p2.x - p1.x;
        final int yDiff = p2.y - p1.y;
        return (int) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
