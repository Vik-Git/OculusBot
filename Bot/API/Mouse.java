package Bot.API;

import Bot.Server.RSPSAppletStub;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Vik on 13/01/2017.
 */
public class Mouse {
    public static void leftClick(int x,int y){
        RSPSAppletStub.RSApplet.dispatchEvent(new MouseEvent(RSPSAppletStub.RSApplet,MouseEvent.MOUSE_MOVED,System.currentTimeMillis(),0,x,y,1,false));
        RSPSAppletStub.RSApplet.dispatchEvent(new MouseEvent(RSPSAppletStub.RSApplet,MouseEvent.MOUSE_PRESSED,System.currentTimeMillis(), 0, x, y, 1, false, 1));
        RSPSAppletStub.RSApplet.dispatchEvent(new MouseEvent(RSPSAppletStub.RSApplet,MouseEvent.MOUSE_RELEASED,System.currentTimeMillis(), 0, x, y, 1, false, 1));
    }
    public static void leftClick(Point p){
        leftClick(p.x,p.y);
    }

    public static void rightClick(int x,int y){
        RSPSAppletStub.RSApplet.dispatchEvent(new MouseEvent(RSPSAppletStub.RSApplet,MouseEvent.MOUSE_MOVED,System.currentTimeMillis(),0,x,y,1,false));
        RSPSAppletStub.RSApplet.dispatchEvent(new MouseEvent(RSPSAppletStub.RSApplet,MouseEvent.MOUSE_PRESSED,System.currentTimeMillis(), 0, x, y, 1, false, 2));
        RSPSAppletStub.RSApplet.dispatchEvent(new MouseEvent(RSPSAppletStub.RSApplet,MouseEvent.MOUSE_RELEASED,System.currentTimeMillis(), 0, x, y, 1, false, 2));
    }
    public static void rightClick(Point p){
        rightClick(p.x,p.y);
    }
}
