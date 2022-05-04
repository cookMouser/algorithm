package shuati.tree;

public class lc404 {

    private int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        sumLeft(root);
        return sum;
    }

    private void sumLeft(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            if (node.left.left == null && node.left.right == null) {
                sum += node.left.val;
            }
            sumLeft(node.left);
        }

        if (node.right != null) {
            sumLeft(node.right);
        }
    }
}
