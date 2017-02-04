package Bot;


import Bot.Misc.Config;
import Bot.Misc.DirectoryManager;
import Bot.UI.BotFrame;

import javax.swing.*;
import java.io.IOException;
/**
 * Created by Vik on 23/11/2016.
 */
public class OculusBot{

    public static void main(String args[]) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {

        DirectoryManager dm  = new DirectoryManager();
        if(!dm.checkDirectory(Config.userDirectory+Config.home)){
            System.out.println("New user detected - Creating directories");
            dm.createAllDirectories();
        }
        BotFrame botFrame = new BotFrame("OculusBot Alpha Version"+0.1);
        /*Injector inj = new Injector(Config.userDirectory+Config.home+"/Servers/client.jar");
        inj.loadClasses();
        inj.modifyClasses();
        inj.dumpClasses();*/

     /*   SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    JDialog.setDefaultLookAndFeelDecorated(true);

                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
                } catch (Exception e) {
                    System.out.println("Substance Graphite failed to initialize");
                }
                try {
                    BotFrame botFrame = new BotFrame("OculusBot Alpha Version"+0.1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

      /*  */
    }

}
