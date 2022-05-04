package shuati.tree;

import java.util.ArrayList;
import java.util.List;

public class lc257 {

    private List<String> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }
        search(root);
        return res;
    }

    private void search(TreeNode node) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    sb.append(path.get(i));
                } else {
                    sb.append(path.get(i) + "->");
                }
            }
            res.add(sb.toString());
        }
        if (node.left != null) {
            search(node.left);

        }
        if (node.right != null) {
            search(node.right);
        }
        path.remove(path.size() - 1);
    }
}
