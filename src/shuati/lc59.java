package shuati;

public class lc59 {

    public int[][] generateMatrix(int n) {
        int count = 1;
        int[][] res = new int[n][n];
        if (n == 1) {
            res[0][0] = 1;
            return res;
        }
        //起始位置
        int i = 0;
        int j = 0;
        // k表示每一轮循环中边长
        for (int k = n; k > 0; k = k - 2) {
            if (k == 1) {
                res[i][j] = count;
                continue;
            }
            //从左向右
            for (int t = 0; t <= k - 2; t++) {
                res[i][j++] = count++;
            }
            // 从上向下
            for (int t = 0; t <= k - 2; t++) {
                res[i++][j] = count++;
            }
            //从右向左
            for (int t = 0; t <= k - 2; t++) {
                res[i][j--] = count++;
            }
            // 从下向上
            for (int t = 0; t <= k - 2; t++) {
                res[i--][j] = count++;
            }

            //下一轮起始位置
            i++;
            j++;
        }
        return res;
    }
}
