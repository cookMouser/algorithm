package shuati.back.lc90;

import java.util.*;

/**
 * LC: 78. Subsets
 */
class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序然后在迭代中剔除重复元素
        Arrays.sort(nums);
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
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            deque.addLast(nums[i]);
            dfs(nums, i + 1);
            deque.removeLast();
        }
    }
}