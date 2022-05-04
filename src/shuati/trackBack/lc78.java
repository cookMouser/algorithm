package shuati.trackBack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc78 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTrack(nums, 0);
        return res;
    }

    private void backTrack(int[] nums, int index) {
        res.add(new ArrayList<>(deque));

        for (int i = index; i < nums.length; i++) {
            deque.add(nums[i]);
            backTrack(nums, i + 1);
            deque.removeLast();
        }
    }
}
