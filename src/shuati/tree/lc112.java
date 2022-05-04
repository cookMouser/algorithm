package shuati.tree;

public class lc112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasSum(root, targetSum);
    }

    private boolean hasSum(TreeNode node , int target) {
        if (node.left == null && node.right == null) {
            if (target == node.val) {
                return true;
            } else {
                return false;
            }
        }
        boolean res = false;
        if (node.left != null) {
           res = hasSum(node.left, target - node.val);
        }

        if (!res) {
            if (node.right != null) {
                res = hasSum(node.right, target - node.val);
            }
        }
        return res;
    }
}
