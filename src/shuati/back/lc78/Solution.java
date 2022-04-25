package shuati.back.lc78;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC: 78. Subsets
 */
class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        // 添加deque已有的元素
        res.add(new ArrayList<>(deque));

        if (start == nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            deque.addLast(nums[i]);
            dfs(nums, i + 1);
            deque.removeLast();
        }
    }
}