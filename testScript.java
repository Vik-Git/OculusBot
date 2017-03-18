import Bot.API.Game;
import Bot.Script.Script;

import java.awt.*;

/**
 * Created by Vik on 23/11/2016.
 */
public class testScript extends Script {
    private Color runeColor = new Color(129, 16, 7);

    public int loop(){
        System.out.println("HP: "+ Game.getCurrentHP());
        System.out.println("Prayer: " + Game.getCurrentPrayer());
        System.out.println("Energy: "+ Game.getEnergy());
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