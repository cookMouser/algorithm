package shuati.dynamicPrograming;


public class lc674 {

    // 双指针解法
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return 1;
        }

        int res = 0;
        // 递增子序列范围为[s, f)
        int s = 0;
        int f = 1;
        for (;f < n; f++) {
            if (nums[f] <= nums[f - 1]) {
                res = Math.max(res, f - s);
                s = f;
            }
        }
        // 维护最后一轮
        if (s < n - 1) {
            res = Math.max(res, f - s);
        }
        return res;
    }

    // dp解法
    public int findLengthOfLCIS2(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];

        dp[0] = 1;
        int res = dp[0];


        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            res = Math.max(res , dp[i]);
        }
        return res;
    }
}
