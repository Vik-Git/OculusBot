package Bot;


import Bot.Misc.Config;
import Bot.Misc.DirectoryManager;
import Bot.Script.testScript;
import Bot.UI.BotFrame;

import java.io.IOException;

/**
 * Created by Vik on 23/11/2016.
 */
public class OculusBot{

    public static void main(String args[]) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        DirectoryManager dm  = new DirectoryManager();
        if(!dm.checkDirectory(Config.userDirectory+Config.home)){
            System.out.println("New user detected - Creating directories");
            dm.createAllDirectories();
        }

        /*Injector inj = new Injector(Config.userDirectory+Config.home+"/Servers/client.jar");
        inj.loadClasses();
        inj.modifyClasses();
        inj.dumpClasses();*/
        BotFrame botFrame = new BotFrame("OculusBot "+0.1);
        Thread t = new Thread(new testScript());
        t.start();
    }

}
