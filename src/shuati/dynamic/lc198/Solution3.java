package shuati.dynamic.lc198;

/**
 * LC: 198. House Robber
 * 动态规划
 */
class Solution3 {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 表示[i, n - 1]范围内元素所能求得的rob最大和
        int[] dp = new int[nums.length];
        // 注意dp数组的含义。此时最后一个元素的值不是0而是nums[nums.length - 1]
        dp[nums.length - 1] = nums[nums.length - 1];


        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i; j <= nums.length - 1; j++) {
                if (j + 2 < nums.length) {
                    dp[i] = Math.max(dp[i], nums[j] + dp[j + 2]);
                } else {
                    dp[i] = Math.max(dp[i], nums[j]);
                }
            }
        }
        return dp[0];
    }

}
