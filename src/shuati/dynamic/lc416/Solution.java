package shuati.dynamic.lc416;

/**
 * LC: 416 Partition Equal Sbuset Sum
 * 在n个物品中选出一定物品，填满sum/2的背包
 */
class Solution {

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum%2 != 0) {
            return false;
        }

        // dp[i][j] 表示是否存在从[0,i]范围挑选任意元素，其和等于j
        boolean[][] dp = new boolean[n][sum / 2 + 1];

        for (int i = 0; i <= sum / 2; i++) {
            if (i == nums[0]) {
                dp[0][i] = true;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum /2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[n - 1][sum / 2];
    }
}
