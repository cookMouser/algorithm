package shuati.tree;



public class lc450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) { // 没有找到
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = min(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;
            return successor;
        }
        return root;

    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }
}
