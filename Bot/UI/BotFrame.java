package Bot.UI;

import Bot.Server.RSPSAppletStub;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
//import java.awt.*;
/**
 * Created by Vik on 24/11/2016.
 */
public class BotFrame extends JFrame{

    private Dimension gameDimension =new Dimension(765,503);


    public BotFrame(String title) throws IllegalAccessException, InstantiationException {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu menuFile = new JMenu("File");
        JMenu menuScript= new JMenu("Script");
        JMenu menuDev = new JMenu("Development");
        JMenu menuHelp = new JMenu("Help");

        menuBar.add(menuFile);
        menuBar.add(menuScript);
        menuBar.add(menuDev);
        menuBar.add(menuHelp);

        JMenuItem itemServer = new JMenuItem("Select Server");
        JMenuItem itemScript = new JMenuItem("Select Script");
        JMenuItem itemStart = new JMenuItem("Start");
        JMenuItem itemStop = new JMenuItem("Stop");
        JMenuItem itemShowLog = new JMenuItem("Show Console");
        JMenuItem itemShowMouse = new JMenuItem("Log Mouse");

        menuFile.add(itemServer);
        menuFile.add(itemScript);
        menuScript.add(itemStart);
        menuScript.add(itemStop);
        menuDev.add(itemShowLog);
        menuDev.add(itemShowMouse);

        this.setVisible(true);
        this.setResizable(true);
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(createGamePanel());
        this.pack();
    }

    private JPanel createGamePanel() throws IllegalAccessException, InstantiationException {
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setPreferredSize(gameDimension);
        gamePanel.add(getGameApplet());
        return gamePanel;
    }

    public Applet getGameApplet() throws IllegalAccessException, InstantiationException {
        Applet RSPSApplet =new RSPSAppletStub().getGameApplet();;
        return  RSPSApplet;
    }
    RSPSAppletStub a = new RSPSAppletStub();
    public Applet test(){
        return a.getGameApplet();
    }

}
