package shuati.dynamicPrograming;

public class lc63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 当前索引位置到终点的路径总数
        int[][] dp = new int[m][n];

        if (obstacleGrid[m - 1][n - 1] == 0) {
            dp[m - 1][n - 1] = 1;
        } else {
            dp[m - 1][n - 1] = 0;
        }

        for (int i = m - 2; i >= 0; i--) {
            if (obstacleGrid[i][n - 1] == 0) {
                dp[i][n - 1] = dp[i + 1][n - 1];
            } else {
                dp[i][n - 1] = 0;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (obstacleGrid[m - 1][i] == 0) {
                dp[m - 1][i] = dp[m - 1][i + 1];
            } else {
                dp[m - 1][i] = 0;
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[0][0];
    }
}
