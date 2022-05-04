package shuati.tree;

import java.util.LinkedList;
import java.util.Queue;

public class lc111 {

    public class Element {
        public TreeNode treeNode;
        public int depth;

        public Element(TreeNode treeNode, int depth) {
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(root, 1));
        while (!queue.isEmpty()) {
            Element e = queue.poll();
            TreeNode node = e.treeNode;
            if (node.left == null && node.right == null) {
                return e.depth;
            }
            if (node.left != null) {
                queue.offer(new Element(node.left, e.depth + 1));
            }
            if (node.right != null) {
                queue.offer(new Element(node.right, e.depth + 1));
            }
        }
        return 0;
    }
}
