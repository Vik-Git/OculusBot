import Bot.API.Game;
import Bot.API.Screen;
import Bot.Script.Script;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 23/11/2016.
 */
public class testScript extends Script {
    private Color runeColor = new Color(129, 16, 7);

    public int loop(){
        BufferedImage img = Screen.getCompleteGameScreen();
        System.out.println("test");
        System.out.println(Game.getCurrentHP(img));
        return 1000;
    }


    public void onStart() {
//s
        System.out.println("booting script");
    }


    public void onStop() {
        System.out.println("Exiting thread");
    }
}