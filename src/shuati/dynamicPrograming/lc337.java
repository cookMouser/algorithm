package shuati.dynamicPrograming;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.Map;

public class lc337 {

    private Map<TreeNode, Map<Boolean, Integer>> map = new HashMap<>();

    public int rob(TreeNode root) {
        return rob2(root, true);
    }

//    private int rob(TreeNode treeNode, boolean canRob) {
//        if (treeNode == null) {
//            return 0;
//        }
//        // 不偷
//        int res = rob(treeNode.left, true) + rob(treeNode.right, true);
//
//
//        // 偷
//        if (canRob) {
//            res = Math.max(treeNode.val + rob(treeNode.left, false) + rob(treeNode.right, false), res);
//        }
//        return res;
//    }

    private int rob2(TreeNode treeNode, boolean canRob) {
        if (treeNode == null) {
            return 0;
        }
        if (map.get(treeNode) != null && map.get(treeNode).get(canRob) != null) {
            return map.get(treeNode).get(canRob);
        }
        // 不偷
        int res = rob2(treeNode.left, true) + rob2(treeNode.right, true);


        // 偷
        if (canRob) {
            res = Math.max(treeNode.val + rob2(treeNode.left, false) + rob2(treeNode.right, false), res);
        }

        if (map.get(treeNode) == null) {
            map.put(treeNode, new HashMap<>());
        }
        map.get(treeNode).put(canRob, res);
        return res;
    }
}
