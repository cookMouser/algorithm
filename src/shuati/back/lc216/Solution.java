package shuati.back.lc216;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC: 216. Combination Sum III
 */
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, 0, n, 1);
        return res;
    }

    /**
     *
     * @param depth  深度
     * @param target  和
     * @param start  开始值 --不能重复
     */
    private void dfs(int k, int depth, int target, int start) {
        System.out.println("depth: " + depth + " target: " + target + " start: " + start);
        if (depth == k && target == 0) {
            // 千万注意要重新new一个对象
            res.add(new ArrayList<>(deque));
        }

        if (depth == k || target == 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i > target) {
                break;
            } else {
                deque.addLast(i);
                dfs(k,depth + 1, target - i, i + 1);
                deque.removeLast();
            }
        }
    }
}
