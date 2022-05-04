package shuati.tree;

import java.util.ArrayList;
import java.util.List;

public class lc538 {

//    public TreeNode convertBST(TreeNode root) {
//        List<TreeNode> list = new ArrayList<>();
//        inOrder(root, list);
//        int sum = 0;
//        for (int i = list.size() - 1; i >= 0; i--) {
//            sum += list.get(i).val;
//            list.get(i).val = sum;
//        }
//        return root;
//    }
//
//    private void inOrder(TreeNode node, List<TreeNode> list) {
//        if (node == null) {
//            return;
//        }
//
//        inOrder(node.left, list);
//        list.add(node);
//        inOrder(node.right, list);
//    }

    private TreeNode pre;

    public TreeNode convertBST(TreeNode root) {
        otherOrder(root);
        return root;
    }

    private void otherOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        otherOrder(node.right);

        if (pre != null) {
            node.val += pre.val;
        }
        pre = node;

        otherOrder(node.left);
    }
}
