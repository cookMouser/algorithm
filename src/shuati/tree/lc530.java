package shuati.tree;

public class lc530 {

    private int diff = Integer.MAX_VALUE;
    private TreeNode pre;

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return diff;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        // 中序遍历，元素是升序排列的，每次只需要计算遍历出的元素与上一个元素的差值
        inOrder(node.left);
        if (pre != null) {
            diff = Math.min(diff, node.val - pre.val);
        }
        pre = node;
        inOrder(node.right);

    }
}
