package shuati.dynamicPrograming;

public class lc53 {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i]表示以nums[i]结尾的最大和连续子数组
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i -1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
