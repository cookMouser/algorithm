package shuati.trackBack;

import java.util.*;

public class lc51 {

    private char[][] temp;
    private List<List<String>> res = new ArrayList<>();

//    private boolean[] rows;
    private boolean[] cols;
    private boolean[] line1;
    private boolean[] line2;
    // 使其中任意两个皇后都不同列、同行和在一条斜线上
    public List<List<String>> solveNQueens(int n) {
//        rows = new boolean[n]S;
        cols = new boolean[n];
        line1 = new boolean[2 * n - 1];
        line2 = new boolean[2 * n - 1];

        temp = new char[n][n];
        for (char[] c : temp) {
            Arrays.fill(c, '.');
        }
        backTrack(n , 0);
        return res;
    }

    private void backTrack(int n, int row) {
        if (row == n) {
            List<String> ret = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(temp[i][j]);
                }
                ret.add(sb.toString());
            }
            res.add(ret);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isValid(n, row, j)) {
                temp[row][j] = 'Q';
                cols[j] = true;
                line1[row + j] = true;
                line2[j - row + n - 1] = true;
                backTrack(n, row + 1);
                temp[row][j] = '.';
                cols[j] = false;
                line1[row + j] = false;
                line2[j - row + n - 1] = false;
            }
        }

    }

    private boolean isValid(int n, int i, int j) {
        return !cols[j] && !line1[i + j] && !line2[j - i + n - 1];
    }
}
