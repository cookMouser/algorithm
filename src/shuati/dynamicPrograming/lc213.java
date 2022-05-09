package shuati.dynamicPrograming;

public class lc213 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 包含头不包含尾
        int[] newNums = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            newNums[i] = nums[i];
        }

        int res1 = myrob(newNums);

        for (int i = 1; i < n; i++) {
            newNums[i - 1] = nums[i];
        }

        int res2 = myrob(newNums);

        return Math.max(res2, res1);


    }

    public int myrob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[n - 1];
    }
}
