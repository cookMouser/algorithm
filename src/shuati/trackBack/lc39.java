package shuati.trackBack;

import java.util.*;

public class lc39 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();
    private int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTrack(candidates, target, 0);
        return res;
    }

    private void backTrack(int[] candidates, int target, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = start; i < candidates.length && sum + candidates[i] <= target; i++) {
            sum += candidates[i];
            deque.addLast(candidates[i]);
            backTrack(candidates, target, i);
            sum -= candidates[i];
            deque.removeLast();
        }
    }
}
