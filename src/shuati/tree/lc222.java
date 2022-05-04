package shuati.tree;

public class lc222 {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        //左子树的深度等于右子树的深度，左边为满二叉树
        if (leftDepth == rightDepth) {
            return (1 << leftDepth)- 1 + countNodes(root.right) + 1;
        } else {
            //左子树的深度大于右子树的深度，右边为满二叉树（完全二叉树不可能出现右子树深度大于左子树）
            return (1 << rightDepth) - 1 + countNodes(root.left) + 1;
        }
    }

    // 求树深度，即最大高度
    // 完全二叉树，只需要不断找left
    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }
}
