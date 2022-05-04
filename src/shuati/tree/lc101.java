package shuati.tree;

import java.util.ArrayList;
import java.util.List;

public class lc101 {

    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            return compare(left.left, right.right) && compare(left.right, right.left);
        }

        if (left == null || right == null) {
            return false;
        }
        return true;
    }

}
