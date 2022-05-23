package shuati.dynamicPrograming;

public class lc188 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) {
            return 0;
        }

        int[][] dp = new int[k + 1][2];

        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = -prices[0];
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int count = k; count > 0; count--) {
                dp[count][0] = Math.max(dp[count][1] + prices[i], dp[count][0]);
                // 千万注意k>=1，而且k = 0利润肯定为0，其实不需要刷新数据了，遍历可以不处理了
                dp[count][1] = Math.max(dp[count - 1][0] - prices[i], dp[count][1]);
            }
        }

        return dp[k][0];
    }

}
