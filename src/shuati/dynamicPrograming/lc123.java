package shuati.dynamicPrograming;

public class lc123 {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[3][2];

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = -prices[0];
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int k = 2; k > 0; k--) {
                dp[k][0] = Math.max(dp[k][1] + prices[i], dp[k][0]);
                // 千万注意k>=1，而且k = 0利润肯定为0，其实不需要刷新数据了，遍历可以不处理了
                dp[k][1] = Math.max(dp[k - 1][0] - prices[i], dp[k][1]);
            }
        }

        return dp[2][0];
    }
}
