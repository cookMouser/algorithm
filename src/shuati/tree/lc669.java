package shuati.tree;

public class lc669 {

    // 修剪root为根的树，返回新的根
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            // 直接删掉左子树，返回右子树删除后的结果
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            // 直接删除右子树，返回左子树删除后的结果
            return trimBST(root.left, low, high);
        } else {
            // 如果再区间内，则更新左右子树为删除后的结果
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
