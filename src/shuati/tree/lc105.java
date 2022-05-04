package shuati.tree;

public class lc105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    // 前序遍历确定根；中序遍历分割左右子树
    private TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft == preRight) {
            return new TreeNode(preorder[preLeft]);
        }

        TreeNode cur = new TreeNode(preorder[preLeft]);

        int i;
        for (i = inLeft; i <= inRight; i++) {
            if (inorder[i] == preorder[preLeft]) {
                break;
            }
        }

        if (i > inLeft) {
            cur.left = build(preorder, inorder, preLeft + 1, i - inLeft + preLeft, inLeft, i - 1);
        }

        if (i < inRight) {
            cur.right = build(preorder, inorder, i - inLeft + preLeft + 1, preRight, i + 1, inRight);
        }

        return cur;
    }
}
