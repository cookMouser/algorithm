package shuati.tree;


import java.util.LinkedList;
import java.util.Queue;

public class lc513 {

    private class Ele {
        public TreeNode treeNode;
        public int cent;
        public Ele(TreeNode treeNode, int cent) {
            this.treeNode = treeNode;
            this.cent = cent;
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        int count = 0;
        int res = -1;
        Queue<Ele> queue = new LinkedList<>();
        queue.offer(new Ele(root, 1));
        while (!queue.isEmpty()) {
            Ele cur = queue.poll();
            TreeNode node = cur.treeNode;
            if (cur.cent > count) {
                res = node.val;
                count++;
            }
            if (node.left != null) {
                queue.offer(new Ele(node.left, cur.cent + 1));
            }
            if (node.right != null) {
                queue.offer(new Ele(node.right, cur.cent + 1));
            }
        }
        return res;
    }
}
