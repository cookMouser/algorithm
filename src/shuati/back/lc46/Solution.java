package shuati.back.lc46;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC : 46 Permutations
 */
class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    // 一般如果要获取的结果是一个二维数组，则需要使用此辅助结构。避免过程中重复创建数组
    private Deque<Integer> deque = new LinkedList<>(); // 存放已经遍历的元素，回溯时需要removeLast

    private boolean[] visited; // 标记元素是否已遍历过

    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        back(nums, 0);
        return res;
    }

    /**
     *
     * @param nums 原始数组。注意过程中不好去删除nums的数据，中途不好处理
     * @param count 当前已经放入队列的数据量
     */
    private void back(int[] nums, int count) {
        if (count == nums.length) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                deque.addLast(nums[i]);
                back(nums, count + 1);
                // 向上回溯还原
                visited[i] = false;
                deque.removeLast();
            }
        }
    }
}
