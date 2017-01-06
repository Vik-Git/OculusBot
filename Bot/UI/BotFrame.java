package Bot.UI;

import Bot.Misc.Config;
import Bot.Server.RSPSAppletStub;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.*;
/**
 * Created by Vik on 24/11/2016.
 */
public class BotFrame extends JFrame{

    private Dimension gameDimension =new Dimension(770,555);
    private JPanel gamePanel = new JPanel(new BorderLayout());
    public BotFrame(String title) throws IllegalAccessException, InstantiationException {
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
        ServerSelection ss =new ServerSelection();
        ScriptSelection scrs =new ScriptSelection();
        ss.setVisible(false);
        scrs.setVisible(false);

        itemScript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrs.setVisible(true);
            }
        });

        itemServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ss.setVisible(true);
            }
        });

        JButton test = (JButton) ss.getContentPane().getComponent(0);
            test.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Config.selectedServer="SP";
                    try {
                        redrawPanel();
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                    ss.setVisible(false);
                }
            });
    }

    private void redrawPanel() throws InstantiationException, IllegalAccessException {
        gamePanel.removeAll();
        if(!Config.selectedServer.equals("")) {
            RSPSAppletStub a = new RSPSAppletStub();
            gamePanel.add(getGameApplet());
            this.revalidate();
        }
    }


    private JPanel createGamePanel() throws IllegalAccessException, InstantiationException {
        gamePanel.setPreferredSize(gameDimension);
        if(!Config.selectedServer.equals("")) {
            RSPSAppletStub a = new RSPSAppletStub();
            gamePanel.add(getGameApplet());
        }
        return gamePanel;
    }

    public Applet getGameApplet() throws IllegalAccessException, InstantiationException {
        Applet RSPSApplet =new RSPSAppletStub().getGameApplet();;
        return  RSPSApplet;
    }

}
