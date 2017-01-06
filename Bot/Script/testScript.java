package Bot.Script;

import Bot.API.ColorUtil;

import java.awt.*;

/**
 * Created by Vik on 23/11/2016.
 */
public class testScript extends Script {
    private Color test = new Color(188, 187, 48);
    private  Color test2= new Color(217,215,55);
    private  Color test3 = new Color(233,220,57);
    int x = 0;
    public int loop(){
        if(x>5){
            System.out.println("doing game screen");
            ColorUtil.gameScreen();
        }
        x++;
        System.out.println(x);
        return 5000;
    }


    void onStart() {
        System.out.println("booting script");
    }


    void onStop() {
        System.out.println("Exiting thread");
    }
}
