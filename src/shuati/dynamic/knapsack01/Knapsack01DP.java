package shuati.dynamic.knapsack01;

/**
 * 01背包问题
 */
public class Knapsack01DP {

    public int knapsack01(int[] w, int[] v, int c) {
        int n = w.length;
        int[][] bp = new int[n][c + 1];

        for (int i = 0; i <= c; i++) {
            if (i >= w[0]) {
                bp[0][i] = v[0];
            } else {
                bp[0][i] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                bp[i][j] = bp[i - 1][j];
                // 注意这里加的条件判断
                if (j >= w[i]) {
                    bp[i][j] = Math.max(bp[i - 1][j], v[i] + bp[i - 1][j - w[i]]);

                }
            }
        }
        return bp[n - 1][c];
    }

}
