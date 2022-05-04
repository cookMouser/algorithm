package shuati.tree;

public class lc617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return merge(root1, root2);
    }

    private TreeNode merge(TreeNode node1, TreeNode node2) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        TreeNode node = new TreeNode(node1.val + node2.val);

        node.left = merge(node1.left, node2.left);
        node.right = merge(node1.right, node2.right);
        return node;
    }
}
