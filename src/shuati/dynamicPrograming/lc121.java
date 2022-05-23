package shuati.dynamicPrograming;


import java.util.PriorityQueue;

public class lc121 {

    // 当前思路是，最大利润且只能买卖一次，实际是查找差值最大的两个数
    // 在一次遍历中，维护求得最小数，使用当前数减去最小数即可。
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0;
        for (int i = 1; i < n; i++) {
            pq.add(prices[i - 1]);
            if (prices[i] - pq.peek() > res) {
                res = prices[i] - pq.peek();
            }
        }
        return res;
    }

    // 动态规划的思路
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[2][2]; // dp[k][j] : k为可交易次数；j为持有股票数
        // 当i（第几天）为0
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            // k 为0时不需要更新值了
            dp[1][0] = Math.max(dp[1][1] + prices[i], dp[1][0]);
            dp[1][1] = Math.max(dp[0][0] - prices[i], dp[1][1]);
        }

        return dp[1][0];
    }

    // 动态规划上再优化空间
    // k为0时利润就是0，那么只需要维护一个k为1的一维数组
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int[] dp = new int[2]; // dp[k][j] : k为可交易次数；j为持有股票数
        // 当i（第几天）为0
        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 1; i < n; i++) {
            // k 为0时不需要更新值了
            dp[0] = Math.max(dp[1] + prices[i], dp[0]);
            dp[1] = Math.max(- prices[i], dp[1]);
        }

        return dp[0];
    }
}
