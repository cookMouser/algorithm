package shuati.dynamicPrograming;

public class lc70 {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    // 使用多重背包
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];

        int[] nums = new int[]{1, 2};

        dp[0] = 1;

        for (int j = 0; j <= n; j++) {
            for (int i = 0; i < 2; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[n];
    }
}
