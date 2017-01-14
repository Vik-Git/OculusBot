import Bot.API.ColorUtil;
import Bot.Script.Script;

import java.awt.*;

/**
 * Created by Vik on 23/11/2016.
 */
public class testScript extends Script {
    private Color bananaColor = new Color(208, 206, 52);
    public int loop(){
        ColorUtil.clickAtGameColor(bananaColor,5);
        System.out.println("end of iteration");
        return 1000;
    }


    public void onStart() {
        System.out.println("booting script");
    }


    public void onStop() {
        System.out.println("Exiting thread");
    }
}
