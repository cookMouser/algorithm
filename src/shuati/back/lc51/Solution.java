package shuati.back.lc51;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] colVisited;
    // 斜向上对角线 索引为[0, 2*n - 2]，值为row + column
    private boolean[] d1;
    // 斜向下对角线 索引为[0, 2*n - 2]，值为row - column + n - 1
    private boolean[] d2;
    private int count = 0;


    public int totalNQueens(int n) {
        List<List<String>> finalRes = new ArrayList<>();
        colVisited = new boolean[n];
        d1 = new boolean[2 * n - 1];
        d2 = new boolean[2 * n - 1];
        dfs(n, 0);
        return count;
    }


    private void dfs(int n, int row) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!colVisited[col] && !d1[row+ col] && !d2[row- col + n - 1]) {
                colVisited[col] = true;
                d1[row+ col] = true;
                d2[row- col + n - 1] = true;
                dfs(n, row + 1);
                colVisited[col] = false;
                d1[row+ col] = false;
                d2[row- col + n - 1] = false;
            }
        }
    }
}
