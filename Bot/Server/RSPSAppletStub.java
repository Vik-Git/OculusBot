package Bot.Server;

import Bot.Accesors.Client;
import Bot.Misc.Config;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;


/**
 * Created by Vik on 24/11/2016.
 */
public class RSPSAppletStub implements AppletStub{
    public static Applet RSApplet;
    public static Client client;
    private Class clientClass;

    public RSPSAppletStub(String fileName) throws IllegalAccessException, InstantiationException {
       RSPSLoader loader = new RSPSLoader(Config.userDirectory+Config.home+"/Servers/"+fileName);
       clientClass =loader.loaddClass("client");
        createApplet();
        startApplet();
    }

    private void startApplet(){
        RSApplet.setStub(this);
        RSApplet.init();
        RSApplet.start();
    }

    private  void createApplet(){
        try {
            RSApplet = (Applet) clientClass.newInstance();
            //client = (Client) RSApplet;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Applet getGameApplet(){
        return RSApplet;
    }

    public boolean isActive() {
        return false;
    }
    public URL getDocumentBase() {
       return null;
    }
    public URL getCodeBase() {
        return null;
    }
    public String getParameter(String name) {
       return null;
    }
    public AppletContext getAppletContext() {
        return null;
    }
    public void appletResize(int width, int height) {
        RSApplet.resize(width,height);
    }

}
