package shuati.dynamic.lc343;

/**
 * LC: 343. Integer Break
 * 动态规划
 */
class Solution {
    public int integerBreak(int n) {
        assert (n >= 2);
        int[] dp = new int[n + 1];
        dp[1] = 1;
//        dp[2] = 1;

        // 递推表达式 f(n) = Math.max((n -1) * f(1), (n -1) * 1, (n -2) * f(2), (n -2) * 2... 1 * f(n - 1), 1 * (n - 1))
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j ++) {
                // 子元素拆分
                dp[i] = Math.max(dp[i], dp[j] * (i - j));
                // 子元素不拆分
                // 该情况容易漏掉，当前dp数组存放的是元素如果拆分得到的最大乘积，还要单独考虑子元素直接不再拆分的情况
                dp[i] = Math.max(dp[i], j * (i - j));
            }
        }
        return dp[n];
    }
}
