package shuati.dynamicPrograming;

public class lc300 {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // dp[i]表示以nums[i]结尾的递增子序列的最大长度
        int[] dp = new int[n];
        dp[0] = 1;
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            // 注意一下单个值作为子序列长度值为1
            dp[i] = 1;
            for (int k = 0; k < i; k++) {
                if (nums[i] > nums[k]) {
                    dp[i] = Math.max(dp[k] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
