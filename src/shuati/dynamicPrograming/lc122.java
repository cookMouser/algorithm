package shuati.dynamicPrograming;

public class lc122 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[1] + prices[i], dp[0]);
            dp[1] = Math.max(dp[0] - prices[i], dp[1]);
        }

        return dp[0];
    }
}
