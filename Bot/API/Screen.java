
package Bot.API;

import Bot.Accesors.Client;
import Bot.Accesors.RSImageProducer;
import Bot.Server.RSPSAppletStub;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vik on 21/12/2016.
 */
public class Screen {
    private static Client c = RSPSAppletStub.client;
    private static RSImageProducer[] loginProducers = {c.getProducer3(), c.getProducer4(), c.getProducer5(), c.getProducer5(), c.getProducer6(), c.getProducer6(), c.getProducer7(),
            c.getProducer8(), c.getProducer9(), c.getProducer10(), c.getProducer11()};

    public static BufferedImage getTabScreen() {
        return c.getProducer13().getRenderedImage();
    }

    public static BufferedImage getMiniMapScreen() {
        return c.getProducer14().getRenderedImage();
    }

    public static BufferedImage getGameScreen(){
        return c.getProducer15().getRenderedImage();
    }

    public static BufferedImage getChatScreen() {
        return c.getProducer16().getRenderedImage();
    }

    public static BufferedImage getNewScreen() {
        return c.getProducer17().getRenderedImage();
    }

    public static BufferedImage getLoginScreen() {
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        for (RSImageProducer e : loginProducers) {
            g.drawImage(e.getImage(), e.getY(), e.getX(), null);
        }
        return img;
    }
    public static BufferedImage getCompleteGameScreen(){
        BufferedImage completeGame = new BufferedImage(765,503,BufferedImage.TYPE_INT_RGB);
        Graphics g = completeGame.getGraphics();
        g.drawImage(getGameScreen(),0,0,null);
        g.drawImage(getChatScreen(),0,0,null);
        g.drawImage(getMiniMapScreen(),0,0,null);
        g.drawImage(getTabScreen(),0,0,null);
        return completeGame;
    }
}