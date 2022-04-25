package shuati.back.lc39;

import java.util.*;

/**
 * LC: 39 Combination Sum
 */
class Solution {

    private List<List<Integer>> res = new ArrayList();
    private Deque deque = new LinkedList();

    // 正数数组
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 排序以后可以优化，如果当前索引值的和加起来都大于等于7,那么后面的元素不需要处理了
        Arrays.sort(candidates);
        back(candidates, target, 0, 0);
        return res;
    }

    private void back(int[] candidates, int target, int sum, int start) {

        for (int i =start ; i < candidates.length; i++) {

            if (sum + candidates[i] == target) {
                deque.addLast(candidates[i]);
                res.add(new ArrayList<>(deque));
                deque.removeLast();
            } else if (sum + candidates[i] > target) {
                break;
            } else {
                deque.addLast(candidates[i]);
                back(candidates, target, sum + candidates[i], i);
                deque.removeLast();
            }
        }
    }
}