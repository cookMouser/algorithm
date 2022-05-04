package shuati.trackBack;

import java.util.*;

public class lc90 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backTrack(nums, 0);
        return res;
    }

    private void backTrack(int[] nums, int index) {
        res.add(new ArrayList<>(deque));

        for (int i = index; i < nums.length; i++) {
            // 去重
            if (i > index && nums[i] == nums[i - 1]){
                continue;
            }
            deque.add(nums[i]);
            backTrack(nums, i + 1);
            deque.removeLast();
        }
    }
}
