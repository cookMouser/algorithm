package shuati.tree;

public class lc110 {

    private boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        height(root);
        return isBalanced;
    }

    private int height(TreeNode node) {
        if (isBalanced == false) {
            return -1;
        }
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int max = Math.max(height(node.left), height(node.right)) + 1;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false;
        }
        return max;
    }
}
