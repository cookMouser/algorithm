package shuati.dynamic.lc279;


/**
 * LC: 279. Perfect Squares
 * 动态规划
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        // 递推表达式(动态转换方程) f(n) = Math.min(f(n - 1*1), f(n - 2*2), f(n - 3*3), ... f(n - k*k)) + 1  (k*k <= n)
        // 转换一下f(n) = Math.min(f(n - 1*1), f(n - 2*2), f(n - 3*3), ... f(n - k*k)) + 1
        for (int i = 1; i <= n; i++) {
            dp[i] = n + 1;
            int j = 1;
            while (j*j <= i) {
                // 次数千万注意要 +1
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
                j++;
            }
        }
        return dp[n];
    }

}
