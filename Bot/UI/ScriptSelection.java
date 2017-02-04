package Bot.UI;

import Bot.Misc.Config;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;

/**
 * Created by Vik on 6/01/2017.
 */
public class ScriptSelection extends JDialog implements TreeSelectionListener {
    private JTree tree;
    private DefaultMutableTreeNode top;
    private JScrollPane treeView;
    private String selectedScript="";
    public ScriptSelection(){
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridwidth = 2;
        constraints.gridheight= 1;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        constraints.weightx =100;
        constraints.weighty =100;
        constraints.fill = GridBagConstraints.BOTH;

        this.setTitle("Script Selection");
        top = new DefaultMutableTreeNode("Scripts");
        createNodes(top);
        tree = new JTree(top);
        tree.addTreeSelectionListener(this);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeView = new JScrollPane(tree);
        this.add(treeView, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 1;
        constraints.weighty = 20;
        constraints.weightx = 0;
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.insets = new Insets(0,60,0,0);
        constraints.anchor = GridBagConstraints.EAST;
        JButton openDir = new JButton("Open Script Directory");
        this.add(openDir,constraints);
        constraints.insets = new Insets(0,0,0,10);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.ipadx = 40;
        constraints.gridy= 1;
        JButton select =new JButton("Select");
        select.setName("select script");

        this.add(select,constraints);

        this.pack();
        this.setVisible(true);
        this.setSize(350,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if(e.getPath().getLastPathComponent().toString().endsWith(".class")) {
            selectedScript = e.getPath().getLastPathComponent().toString();
        }
    }

    public String getSelectedScript(){
        if (selectedScript.equals("")){
            JOptionPane.showMessageDialog(this,"No script selected!");
            return "";
        } else {
            return selectedScript;
        }
    }

    public void refreshNodes(){
        treeView.getViewport().remove(tree);
        top = new DefaultMutableTreeNode("Scripts");
        createNodes(top);
        tree = new JTree(top);
        //tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
       // tree.addTreeSelectionListener(this);
        treeView.getViewport().add(tree);
        this.revalidate();
    }

    private void createNodes(DefaultMutableTreeNode top){
        File folder =new File(Config.userDirectory+Config.home+Config.subDirectories[1]);
        File[] scripts = folder.listFiles();
        if(folder.listFiles().length > 0) {
            for (File script : scripts) {
                top.add(new DefaultMutableTreeNode(script.getName()));
            }
        }
    }
}
