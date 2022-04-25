package shuati.dynamic.knapsack01;

/**
 * 01背包问题
 */
public class Knapsack01 {

    private int[][] memo;
    public int knapsack01(int[] w, int[] v, int c) {
        int n = w.length;
        memo = new int[n][c + 1]; //容量的取值范围是[0, c]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c + 1; j ++) {
                memo[i][j] = -1;
            }
        }
        return bestValue(w, v, n, c);

    }

    // 用[0, index]中任意件物品，填充容积为c的背包所得最大价值
    private int bestValue(int[] w, int[] v, int index, int c) {
        if (0 < index || c <= 0) {
            return 0;
        }

        if (memo[index][c] != -1) {
            return memo[index][c];
        }

        int res = bestValue(w, v, index - 1, c);
        if (c > w[index]) {
            res = Math.max(res, bestValue(w, v, index - 1, c - w[index]) + v[index]);
        }
        memo[index][c] = res;
        return res;
    }
}
