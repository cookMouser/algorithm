package shuati.tree;


public class lc106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
       return build(inorder, postorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if (inRight == inLeft) {
            return new TreeNode(inorder[inLeft]);
        }

        TreeNode curRoot = new TreeNode(postorder[postRight]);

        // 寻找根在inorder中的位置，即为左右子树切割点
        int i = inLeft;
        for (; i <= inRight; i++) {
            if (inorder[i] == postorder[postRight]) {
                break;
            }
        }
        // 中序数组左子树范围[inLeft, i - 1] 右子树范围[i + 1, inRight]
        // 后续数组两个子树的范围与中序数组的一样

        if (i > inLeft) {
            // 存在左子树
            curRoot.left = build(inorder, postorder, inLeft, i - 1, postLeft, postLeft + i - 1 - inLeft);
        }

        if (i < inRight) {
            // 存在右子树
            curRoot.right = build(inorder, postorder, i + 1, inRight, postLeft + i - inLeft, postRight - 1);
        }
        return curRoot;
    }
}
