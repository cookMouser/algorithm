package shuati.dynamicPrograming;

public class lc377 {

    public int combinationSum4(int[] nums, int target) {
        int n =nums.length;

        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < n; i++) {
                if (j >= nums[i]) {
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
        }

        return dp[target];
    }
}
