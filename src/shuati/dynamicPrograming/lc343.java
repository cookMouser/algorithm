package shuati.dynamicPrograming;

public class lc343 {

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];  //数i拆分后得到的最大乘积

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
            if (i != n) {
                dp[i] = Math.max(dp[i], i);
            }
        }
        return dp[n];
    }
}
