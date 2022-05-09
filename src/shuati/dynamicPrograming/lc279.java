package shuati.dynamicPrograming;

import java.util.Arrays;

public class lc279 {

    public int numSquares(int n) {
        int[] nums = new int[n];
        int k = 1;
        for (int i = 0; i < n; i++) {
            nums[i] = k++;
        }

        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i % nums[0] == 0) {
                dp[i] = i / nums[0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = nums[i] * nums[i]; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - nums[i] * nums[i]] + 1);
            }
        }

        return dp[n];
    }

}
