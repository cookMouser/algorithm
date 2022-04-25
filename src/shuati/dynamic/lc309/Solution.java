package shuati.dynamic.lc309;

/**
 *
 */
class Solution {
    // 要弄清楚有多少种状态，状态间可以有哪些转化（包括自己转换为自己的场景），每一种转换如何做到。最后得到状态转换方程
    public int maxProfit(int[] prices) {
        int m = prices.length;
        // 倒数i + 1天 ,不同状态下，可以获取的利润最大值
        int[][] dp = new int[prices.length][3];
        dp[m - 1][0] = 0;
        dp[m - 1][1] = 0;
        dp[m - 1][2] = prices[m - 1];

        for (int i = m - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][2] + prices[i]);
            dp[i][1] = dp[i + 1][0];
            dp[i][2] = Math.max(dp[i + 1][2], dp[i + 1][1] - prices[i]);
        }

        return dp[0][0];
    }
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
}
