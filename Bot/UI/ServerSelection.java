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
public class ServerSelection extends JDialog implements TreeSelectionListener {
    private JTree tree;
    private DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
    private JScrollPane treeView;
    private String selectedServer="";
    public ServerSelection(){
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridwidth = 3;
        constraints.gridheight= 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx =100;
        constraints.weighty =100;
        constraints.fill = GridBagConstraints.BOTH;

        this.setTitle("Server Selection");

        createNodes(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);
         treeView = new JScrollPane(tree);
        this.add(treeView, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 1;
        constraints.weighty = 20;
        constraints.gridy = 1;
        constraints.gridx = 0;
        JButton openDir = new JButton("Open Server Directory");
        this.add(openDir,constraints);


        constraints.gridx = 1;
        JButton inject =new JButton("Inject");
        this.add(inject,constraints);

        constraints.gridx = 2;
        constraints.ipadx = 40;
        JButton select =new JButton("Select");
        select.setName("select server");
        this.add(select,constraints);

        this.pack();
        this.setVisible(true);
        this.setSize(400,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void refreshNodes(){
        treeView.getViewport().remove(tree);
        top = new DefaultMutableTreeNode("Servers");
        createNodes(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);
        treeView.getViewport().add(tree);
        this.revalidate();
    }

    private void createNodes(DefaultMutableTreeNode top){
        File folder =new File(Config.userDirectory+Config.home+Config.subDirectories[0]);
        File[] servers = folder.listFiles();
        DefaultMutableTreeNode categoryInj = new DefaultMutableTreeNode("Injected");
        DefaultMutableTreeNode categoryNon = new DefaultMutableTreeNode("Non Injected");
        if(folder.listFiles().length > 0) {
            for (File server : servers) {
                if(server.getName().contains("_inj")) {
                    categoryInj.add(new DefaultMutableTreeNode(server.getName()));
                }else{
                    categoryNon.add(new DefaultMutableTreeNode(server.getName()));
                }
            }
        }
        top.add(categoryNon);
        top.add(categoryInj);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if(e.getPath().getLastPathComponent().toString().endsWith(".jar")) {
            selectedServer = e.getPath().getLastPathComponent().toString();
            System.out.println(selectedServer);
        }
    }

    public String getSelectedServer() {
        if (selectedServer.equals("")){
            JOptionPane.showMessageDialog(this,"No server selected!");
            return "";
        } else {
            return selectedServer;
        }
    }
}
