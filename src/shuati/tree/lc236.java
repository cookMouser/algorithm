package shuati.tree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findPath(root, p, q);
        List<TreeNode> list1 = res.get(0);
        List<TreeNode> list2 = res.get(1);
        int i;
        if (list1.size() <= list2.size()) {
            for (i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    break;
                }
            }
        } else {
            for (i = 0; i < list2.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    break;
                }
            }
        }
        return list1.get(i - 1);
    }

    List<List<TreeNode>> res = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();

    private void findPath(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return;
        }
        deque.addLast(node);
        if (node == p) {
            res.add(new ArrayList<>(deque));
        }

        if (node == q) {
            res.add(new ArrayList<>(deque));
        }

        findPath(node.left, p , q);
        findPath(node.right, p, q);
        deque.removeLast();
    }
}
