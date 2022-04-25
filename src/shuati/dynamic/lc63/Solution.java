package shuati.dynamic.lc63;

/**
 *  LC: 62. Unique Paths ||
 */
class Solution {
    // 递推公式
    //  0、arr[x][y] == 1 => f(x, y) = 0
    //  1、x== m - 1 => f(x, y) = f(x, y + 1)
    //  2、y== n - 1 => f(x, y) = f(x + 1, y)
    //  3、f(x, y) = f(x, y + 1) + f(x + 1, y)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;

        for (int i = m -1; i >= 0; i --) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == m - 1) {
                    dp[i][j] = dp[i][j + 1];
                    continue;
                }
                if (j == n - 1) {
                    dp[i][j] = dp[i + 1][j];
                    continue;
                }
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
            }
        }
        return dp[0][0];
    }
}
