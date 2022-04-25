package shuati.dynamic.lc322;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = -1;
            }
        }
        // 如果dp数组的值为0，则代表无法刚好凑成指定金额
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                for (int k = 0; k <= j / coins[i]; k++) {
                    if (dp[i - 1][j - coins[i] * k] >= 0) {
                        if (dp[i][j] == -1) {
                            dp[i][j] = dp[i - 1][j - coins[i] * k] + k;
                        } else {
                            dp[i][j] = Math.min(dp[i][j],dp[i - 1][j - coins[i] * k] + k);
                        }
                    }
                }
            }
        }
        if (dp[n - 1][amount] >= 0) {
            return dp[n - 1][amount];
        } else {
            return -1;
        }
    }

}