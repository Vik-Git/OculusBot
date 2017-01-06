package Bot.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vik on 6/01/2017.
 */
public class ServerSelection extends JDialog {
    public ServerSelection(){
        this.setSize(300,80);
        this.setResizable(false);
        this.setTitle("Select a Server");
        this.setLayout(new GridBagLayout());
        JButton playSP = new JButton("Launch Soul Play");
        this.add(playSP);
        this.setVisible(true);
    }
}
