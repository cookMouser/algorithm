package shuati.dynamic.lc62;

/**
 *  LC: 62. Unique Paths
 */
class Solution {
    // 递推公式
    //  1、x== m - 1 => f(x, y) = 1
    //  2、y== n - 1 => f(x, y) = 1
    //  3、f(x, y) = f(x, y + 1) + f(x + 1, y)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}