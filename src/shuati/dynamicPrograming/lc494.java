package shuati.dynamicPrograming;

public class lc494 {

    public int findTargetSumWays(int[] nums, int target) {
        // 假设添加正号的数字和为x，添加负号的数字的和为y
        // x + y = sum --sum是数组所有元素的和
        // x - y = target
        // 得到 x = (sum + target) / 2;
        // x即为背包容量，问题转变为从[0, k]区间取子集放入‘正号’背包，有多少种方法
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if ((sum + target) % 2 != 0) { //正数的和肯定是个正整数，如果不能整除直接就找不到
            return 0;
        }

        // 注意此条件
        if (Math.abs(target) > sum) {
            return 0;
        }

        int x = (sum + target) / 2;

        // int[][] dp = new int[nums.length][x + 1];

        int[] dp = new int[x + 1];
        for (int j = 0; j <= x; j++) {
            // 千万注意这里的初始化，如果第一个值为0，那么dp[0]为2
            // 选择加号
            if (nums[0] == j) {
                dp[j]++;
            }
            // 选择减号
            if (0 == j) {
                dp[j]++;
            }
        }

        for (int i = 1; i< nums.length; i++) {
            for (int j = x; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }

        return dp[x];
    }
}
