package Bot.UI;

import Bot.Misc.Config;
import Bot.Server.RSPSAppletStub;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.awt.*;
/**
 * Created by Vik on 24/11/2016.
 */
public class BotFrame extends JFrame implements ActionListener{

    private Dimension gameDimension =new Dimension(770,555);
    private JPanel gamePanel = new JPanel(new BorderLayout());
    private ScriptSelection scrs;
    private ServerSelection ss;
    public BotFrame(String title) throws IllegalAccessException, InstantiationException, IOException {
        JMenuBar menuBar = new JMenuBar();

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
        JMenuItem itemShowLog = new JMenuItem("Console");
        JMenuItem itemShowMouse = new JMenuItem("Mouse");

        menuFile.add(itemServer);
        menuFile.add(itemScript);
        menuScript.add(itemStart);
        menuScript.add(itemStop);
        menuDev.add(itemShowLog);
        menuDev.add(itemShowMouse);

        this.setVisible(true);
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(createGamePanel());
        this.setResizable(false);
        this.pack();
        this.setSize(gameDimension);

        itemScript.addActionListener(this);
        itemServer.addActionListener(this);
    }

    private void redrawPanel() throws InstantiationException, IllegalAccessException {
        gamePanel.removeAll();
        if(!Config.selectedServer.equals("")) {
            RSPSAppletStub a = new RSPSAppletStub();
            gamePanel.add(getGameApplet());
            this.revalidate();
        }
    }


    private JPanel createGamePanel() throws IllegalAccessException, InstantiationException, IOException {
        gamePanel.setPreferredSize(gameDimension);
        System.out.println();
        BufferedImage bg = ImageIO.read(new File(Config.userDirectory+Config.home+Config.subDirectories[3]+"/bg.jpg"));
        JLabel bgLabel = new JLabel(new ImageIcon(bg));
        gamePanel.add(bgLabel);
        return gamePanel;
    }

    public Applet getGameApplet() throws IllegalAccessException, InstantiationException {
        Applet RSPSApplet =new RSPSAppletStub().getGameApplet();;
        return  RSPSApplet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Select Server":
               ss = new ServerSelection();
                break;
            case "Select Script":
                scrs= new ScriptSelection();
                for(Component c: scrs.getContentPane().getComponents()){
                    if(c instanceof JButton){
                    ((JButton) c).addActionListener(this);
                    }
                }
                break;
        }
        if(e.getActionCommand().endsWith(".class")){
            Config.selectedScript =e.getActionCommand();
            Config.selectedScriptPath = Config.userDirectory+Config.home+Config.subDirectories[1]+"/"+e.getActionCommand();
            System.out.println("Selected Script- "+Config.selectedScriptPath);
           scrs.dispose();
        }
    }
}
