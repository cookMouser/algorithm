package shuati.trackBack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc46 {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (deque.size() == nums.length) {
            res.add(new ArrayList<>(deque));
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                deque.add(nums[i]);
                backTrack(nums);
                visited[i] = false;
                deque.removeLast();
            }
        }
    }
}
