package shuati.trackBack;

import java.util.*;

public class lc491 {
    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTrack(nums, 0);
        return res;
    }

    private void backTrack(int[] nums, int index) {
        // 如果index > length是不可能进入当前函数的，直接这里添加结果没问题，不会重复
        // 添加一个set，保存本层横向循环时，使用过的值（注意不是存放索引），不能使用重复的
        Set<Integer> set = new HashSet<>();

        if (deque.size() >= 2) {
            res.add(new ArrayList<>(deque));
        }
        for (int i = index; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            if (!deque.isEmpty() && nums[i] < deque.getLast()) {
                continue;
            }
            deque.addLast(nums[i]);
            set.add(nums[i]);
            backTrack(nums, i + 1);
            deque.removeLast();
        }
    }

}
