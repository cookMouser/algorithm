package shuati.tree;

import java.util.ArrayList;
import java.util.List;

public class lc501 {
    private List<Integer> res = new ArrayList<>(); //存放最大频率的值
    private int maxFre = 0; // 标记最大频率
    private int count = 0; // 记录出现频率（元素值出现的个数）
    private TreeNode pre;

    public int[] findMode(TreeNode root) {
        inOrder(root);
        // 千万注意最后一轮(pre变成了最后一个节点时)要单独处理
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);

        // 维护当前pre、count
        if (pre == null) {
            //第一个root节点
            count = 1;
        } else {
            if (pre.val == node.val) {
                count++;
            } else {
                // 不同元素count更新
                count = 1;
            }
        }
        pre = node;

        // 维护maxFre、res
        if (count > maxFre) {
            maxFre = count;
            res.clear();
            res.add(node.val);
        } else if (count == maxFre) {
            res.add(node.val);
        }

        inOrder(node.right);

    }
}
