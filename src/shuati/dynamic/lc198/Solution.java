package shuati.dynamic.lc198;

/**
 * LC: 198. House Robber
 * 看成了一个排列的问题，实际时不对的。然后又没有查询优化，导致lc过不了
 */
class Solution {
    private boolean[] visited;
    private int max = -1;
    private int sum;

    public int rob(int[] nums) {
        visited = new boolean[nums.length];
        back(nums);
        return max;
    }

    private void back(int[] nums) {
        boolean isBack = false;
        for (int i = 0; i < nums.length; i++) {
            if (canRob(i)) {
                sum += nums[i];
                visited[i] = true;
                isBack = true;
                back(nums);
                sum -= nums[i];
                visited[i] = false;
            }
        }
        if (!isBack) {
            max = Math.max(sum, max);
        }
    }

    private boolean canRob(int n) {
        if (visited[n]) {
            return false;
        }
        if (n - 1 >= 0 && visited[n - 1]) {
            return false;
        }
        if (n + 1 < visited.length && visited[n + 1]) {
            return false;
        }
        return true;
    }

}
