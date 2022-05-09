package shuati.dynamicPrograming;

public class lc416 {

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        // f(k, sum) = f(k - 1, sum) || f(k - 1, sum - nums[k])

        // 在[0, k]区间内是否能挑选出子集的和等于sum
        // boolean[][] dp = new boolean[n][target + 1];
        // dp[k][sum] = dp[k - 1][sum] || dp[k - 1][sum - nums[k]]

        boolean[] dp = new boolean[target + 1];

        // 初始化第一行
        for (int i = 0; i <= target; i++) {
            if (nums[0] == i) {
                dp[i] = true;
            } else {
                dp[i] = false;
            }
        }

        for (int i = 1 ; i < n; i++) {
            for (int j = target; j >= nums[i]; j++) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }
}
