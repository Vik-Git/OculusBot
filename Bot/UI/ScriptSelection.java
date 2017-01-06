package Bot.UI;

import Bot.Misc.Config;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Vik on 6/01/2017.
 */
public class ScriptSelection extends JDialog {
    public ScriptSelection(){
        this.setSize(300,80);
        this.setResizable(false);
        this.setTitle("Select a Script");
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        displayScripts();
    }

    private void displayScripts(){
        File folder =new File(Config.userDirectory+Config.home+Config.subDirectories[1]);
        File[] scripts = folder.listFiles();
        if(folder.listFiles().length > 0) {
            for (File script : scripts) {
                this.add(new JButton(script.getName()));
            }
        }
        this.revalidate();
    }
}
