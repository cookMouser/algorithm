package shuati.dynamic.lc64;

/**
 * LC: 64. Minimum Path Sum
 */
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // 注意遍历顺序，要从最后一个点，先从右往左遍历完，然后上升一层，然后再从右向左，直至遍历完成
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                // 下边界
                if (i == m - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                    continue;
                }
                // 右边界
                if (j == n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][ j];
                    continue;
                }

                dp[i][j] = grid[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return dp[0][0];
    }
}
