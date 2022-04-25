package shuati.dynamic.knapsack01;

/**
 * 01背包问题
 * 空间复杂度优化
 * 数组空间优化为一行，填充的时候从右向左填充
 */
public class Knapsack01DP3 {

    public int knapsack01(int[] w, int[] v, int c) {
        int n = w.length;
        int[] bp = new int[c + 1];

        for (int i = 0; i <= c; i++) {
            if (i >= w[0]) {
                bp[i] = v[0];
            } else {
                bp[i] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = c; j >= w[i]; j--) {
                bp[j] = Math.max(bp[j], v[i] + bp[j - w[i]]);
            }
        }
        return bp[c];
    }

}
