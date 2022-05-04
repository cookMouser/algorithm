package shuati.trackBack;

import java.util.*;

public class lc47 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (deque.size() == nums.length) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {  //纵向去重（同一根树枝上）
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) { //横向去重。同一层向下递归不能使用相同值
                continue;
            }
            deque.addLast(nums[i]);
            visited[i] = true;
            backTrack(nums);
            deque.removeLast();
            visited[i] = false;
        }
    }
}
