package shuati.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc113 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        trackBack(root, targetSum);
        return res;
    }

    private void trackBack(TreeNode node, int targetSum) {
        deque.addLast(node.val);
        if (node.left == null && node.right == null) {
            if (targetSum == node.val) {
                res.add(new ArrayList<>(deque));
            }
        }

        if (node.left != null) {
            // 注意不要在最后叶子节点时候遍历dequeue求和，判断和原始targetSum是否一致，这样会导致前面的元素大量重复计算
            // 而是在每次走过一个节点后只计算一次
            trackBack(node.left, targetSum - node.val);
        }

        if (node.right != null) {
            trackBack(node.right, targetSum - node.val);
        }
        deque.removeLast();
    }
}
