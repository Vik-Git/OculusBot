package Bot.API;

import java.awt.*;

/**
 * Created by Vik on 14/01/2017.
 */
public class Player {

    public static Point getPlayer() throws InterruptedException {
        return new Point(Screen.getGameScreen().getWidth()/2,Screen.getGameScreen().getHeight()/2);
    }
}
