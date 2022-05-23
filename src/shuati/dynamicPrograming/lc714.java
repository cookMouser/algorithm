package shuati.dynamicPrograming;

public class lc714 {

    // 假如我们在同一天去重复买入卖出的动作，实际上增加了不必要的手续费，因此我们在同一天应该只做一个动作
    public int maxProfit(int[] prices, int fee) {
        // 假设是在卖出的时候又手续费
        int n = prices.length;

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[1] + prices[i] - fee, dp[0]);
            dp[1] = Math.max(dp[0] - prices[i], dp[1]);
        }

        return dp[0];
    }
}
