import Bot.API.Combat;
import Bot.API.Screen;
import Bot.Script.Script;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 23/11/2016.
 */
public class testScript extends Script {
    private Color yakColor = new Color(89, 74, 66);
    private BufferedImage screen;

    public int loop() throws InterruptedException {
        screen = Screen.getGameScreen();
        if(Combat.isInCombat(screen)){
            System.out.println("in combat");
        }else{
            JFrame frame = new JFrame("test");
            frame.setSize(new Dimension(screen.getWidth(),screen.getHeight()));
            frame.add(new JLabel(new ImageIcon(screen)));
            frame.setVisible(true);
            System.out.println("non in combat");
        }
        return 10000;
    }


    public void onStart() {

        System.out.println("booting script");
    }


    public void onStop() {
        System.out.println("Exiting thread");
    }
}
/*PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        System.out.println("X: "+x +"Y:"+y);*/