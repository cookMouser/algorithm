package shuati.tree;

public class lc226 {

    public TreeNode invertTree(TreeNode root) {
        myInvertTree(root);
        return root;
    }

    private void myInvertTree(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = right;
        node.right = left;
        myInvertTree(left);
        myInvertTree(right);
    }
}
