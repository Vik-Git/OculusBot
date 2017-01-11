package Bot.UI;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Created by Vik on 6/01/2017.
 */
public class ScriptSelection extends JDialog {
    private JTree tree;
    public ScriptSelection(){
        this.setResizable(false);
        this.setLayout(new GridLayout(1,0));
        this.setTitle("Script Selection");
        DefaultMutableTreeTableNode top = new DefaultMutableTreeTableNode("Scripts");
        createNodes(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane treeView = new JScrollPane(tree);
        this.add(treeView);
        this.pack();
        this.setVisible(true);
        this.setSize(350,500);
    }

    private void createNodes(DefaultMutableTreeTableNode top){
        for(int i = 0; i < 35;i ++) {
            top.add(new DefaultMutableTreeTableNode("testScript"));
        }
    }
}
