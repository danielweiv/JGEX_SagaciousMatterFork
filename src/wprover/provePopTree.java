package wprover;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-21
 * Time: 22:25:06
 * To change this template use File | Settings | File Templates.
 */
public class provePopTree extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3359884663893099908L;
	private DefaultMutableTreeNode top;
    private JTree tree;

    public provePopTree() {
        top = new DefaultMutableTreeNode("", true);
        tree = new JTree(top);
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
}
