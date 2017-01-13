package Bot.UI;

import Bot.Injector;
import Bot.Misc.Config;
import Bot.Script.Script;
import Bot.Script.ScriptLoader;
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
    private Thread t;
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

        itemStart.addActionListener(this);
        itemStop.addActionListener(this);
        itemScript.addActionListener(this);
        itemServer.addActionListener(this);
    }

    private void redrawPanel() throws InstantiationException, IllegalAccessException {
        gamePanel.removeAll();
        if(!Config.selectedServer.equals("")) {
            RSPSAppletStub a = new RSPSAppletStub(Config.selectedServer);
            gamePanel.add(getGameApplet());
            this.pack();
            this.setSize(gameDimension);
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
        Applet RSPSApplet =new RSPSAppletStub(Config.selectedServer).getGameApplet();;
        return  RSPSApplet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Open Script Directory":
                try {
                    Desktop.getDesktop().open(new File(Config.userDirectory+Config.home+Config.subDirectories[1]));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Open Server Directory":
                try {
                    Desktop.getDesktop().open(new File(Config.userDirectory+Config.home+Config.subDirectories[0]));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case"Inject":
                System.out.println(Config.userDirectory+Config.home+Config.subDirectories[0]+"/"+ss.getSelectedServer());
                System.out.println(Config.selectedServer);
                Injector inj = new Injector(Config.userDirectory+Config.home+Config.subDirectories[0]+"/"+ss.getSelectedServer());
                try {
                    inj.loadClasses();
                    inj.modifyClasses();
                    inj.dumpClasses();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                ss.refreshNodes();

                break;
            case "Select Server":
                if(ss ==null) {
                    ss = new ServerSelection();
                    for (Component c : ss.getContentPane().getComponents()) {
                        if (c instanceof JButton) {
                            ((JButton) c).addActionListener(this);
                        }
                    }
                }else{
                    ss.requestFocus();
                    ss.refreshNodes();
                    ss.setVisible(true);
                }
                break;
            case "Select Script":
                if(scrs == null) {
                    scrs= new ScriptSelection();
                    for (Component c : scrs.getContentPane().getComponents()) {
                        if (c instanceof JButton) {
                            ((JButton) c).addActionListener(this);
                        }
                    }
                }else{
                    scrs.requestFocus();
                    scrs.refreshNodes();
                    scrs.setVisible(true);
                }
                break;
            case "Start":
                ScriptLoader l = new ScriptLoader();
                Class scriptClass = l.loaddClass(Config.selectedScript);
                System.out.println(scriptClass);
                Script s = null;
                try {
                    s = (Script) scriptClass.newInstance();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                t = new Thread(s);
                t.start();
                break;
            case "Stop":
                t.interrupt();
                break;
            case "Select":
                if(((JButton)e.getSource()).getName().equals("select server")){
                    Config.selectedServer = ss.getSelectedServer();
                    ss.dispose();
                    try {
                        redrawPanel();
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    Config.selectedScript = scrs.getSelectedScript();
                    Config.selectedScriptPath = Config.userDirectory+Config.home+Config.subDirectories[1]+"/";
                    scrs.dispose();
                }
            break;
            default:
                System.out.println(((JButton) e.getSource()).getName());
            break;
        }
    }
}
