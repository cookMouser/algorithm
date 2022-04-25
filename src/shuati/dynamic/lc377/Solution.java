package shuati.dynamic.lc377;

class Solution {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        // [0, i]范围内，能够凑出和为j的组合数量
        int[][] dp = new int[n][target + 1];

        for (int i = 0; i <= target; i++) {
            if (i % nums[0] == 0) {
                dp[0][i] = 1;
            }
        }
        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                for (int k = 0; k <= j / nums[i]; k++) {
                    dp[i][j] += dp[i - 1][j - nums[i] * k];
                }
            }
        }

        return dp[n - 1][target];
    }
}
