package shuati.back.LC40;

import java.util.*;

/**
 * LC: 40 Combination Sum II
 */
class Solution {

    private List<List<Integer>> res = new ArrayList();
    private Deque deque = new LinkedList();

    // 正数数组
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序以后可以优化，如果当前索引值的和加起来都大于等于7,那么后面的元素不需要处理了
        Arrays.sort(candidates);
        back(candidates, target, 0);
        return res;
    }

    /**
     *
     * @param candidates
     * @param target
     * @param start 新一轮开始的索引，比前一轮值+1
     */
    private void back(int[] candidates, int target, int start) {

        for (int i =start ; i < candidates.length; i++) {
            // 同一个节点的下一层不能使用相同值的元素，否则最后的排列会重复
            // 千万注意条件是i > start 而不是i > 0。加入 start和start - 1两个位置的值相同，那么start的元素就被跳过了，不合逻辑
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] == target) {
                deque.addLast(candidates[i]);
                res.add(new ArrayList<>(deque));
                deque.removeLast();
            } else if (candidates[i] > target) {
                break;
            } else {
                deque.addLast(candidates[i]);
                back(candidates,target - candidates[i], i + 1);
                deque.removeLast();
            }
        }
    }
}