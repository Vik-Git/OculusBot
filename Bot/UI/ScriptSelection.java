package Bot.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vik on 6/01/2017.
 */
public class ScriptSelection extends JDialog {
    public ScriptSelection(){
        this.setSize(300,80);
        this.setResizable(false);
        this.setTitle("Select a Script");
        this.setLayout(new GridBagLayout());
        JButton playSP = new JButton("TestScript");
        this.add(playSP);
        this.setVisible(true);
    }
}
