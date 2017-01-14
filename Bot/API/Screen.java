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
    static Client c = RSPSAppletStub.client;

    private static RSImageProducer[] loginProducers = {c.getProducer3(),c.getProducer4(),c.getProducer5(),c.getProducer5(),c.getProducer6(),c.getProducer6(),c.getProducer7(),
            c.getProducer8(), c.getProducer9(),c.getProducer10(),c.getProducer11()};


    public static BufferedImage getTabScreen(){
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
            g.drawImage(c.getProducer13().getImage(),c.getProducer13().getY(),c.getProducer13().getX(),null);
        return img;
    }
    public static BufferedImage getMiniMapScreen(){
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.drawImage(c.getProducer14().getImage(),c.getProducer14().getY(),c.getProducer14().getX(),null);
        return img;
    }
    public static BufferedImage getGameScreen(){
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.drawImage(c.getProducer15().getImage(),c.getProducer15().getY(),c.getProducer15().getX(),null);
        return img;
    }

    public static BufferedImage getChatScreen(){
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.drawImage(c.getProducer16().getImage(),c.getProducer16().getY(),c.getProducer16().getX(),null);
        return img;
    }

    public static BufferedImage getNewScreen(){
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.drawImage(c.getProducer17().getImage(),c.getProducer17().getY(),c.getProducer17().getX(),null);
        return img;
    }

    public static BufferedImage getLoginScreen(){
        BufferedImage img = new BufferedImage(RSPSAppletStub.RSApplet.getWidth(),RSPSAppletStub.RSApplet.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        for (RSImageProducer e: loginProducers) {
            g.drawImage(e.getImage(),e.getY(),e.getX(),null);
        }
        return img;
    }


}
