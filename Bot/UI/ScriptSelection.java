package Bot.UI;

import Bot.Misc.Config;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;

/**
 * Created by Vik on 6/01/2017.
 */
public class ScriptSelection extends JDialog {
    private JTree tree;
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
        DefaultMutableTreeTableNode top = new DefaultMutableTreeTableNode("Scripts");
        createNodes(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane treeView = new JScrollPane(tree);
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
        this.add(select,constraints);

        this.pack();
        this.setVisible(true);
        this.setSize(350,500);
    }

    private void createNodes(DefaultMutableTreeTableNode top){
        File folder =new File(Config.userDirectory+Config.home+Config.subDirectories[1]);
        File[] scripts = folder.listFiles();
        if(folder.listFiles().length > 0) {
            for (File script : scripts) {
                top.add(new DefaultMutableTreeTableNode(script.getName()));
            }
        }
    }
}
