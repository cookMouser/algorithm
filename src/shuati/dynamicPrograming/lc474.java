package shuati.dynamicPrograming;

public class lc474 {

    public int findMaxForm(String[] strs, int m, int n) {

        // f(k, m, n) = Math.max(f(k - 1, m - 0length, n - 1length) + 1), f(k - 1, m, n));
        // 如果一个三维数组看作立方体，把k看作高，那么可以状态压缩，每一轮都在同一层(m + 1 x n + 1 组成的二维平面)去覆盖数据
        //f(m, n) = Math.max(f(m - 0length, n - 1length) + 1), f(m, n));

        int[][] dp = new int[m + 1][n + 1];

        String one = strs[0];
        int[] oneNum = getOneAndZeroNum(one);
        // 初始化
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (oneNum[0] <= i && oneNum[1] <= j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int k = 1; k < strs.length; k++) {
            int[] num = getOneAndZeroNum(strs[k]);
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (num[0] <= i && num[1] <= j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - num[0]][j - num[1]] + 1);
                    }
                }
            }
        }

        return dp[m][n];
    }

    private int[] getOneAndZeroNum(String s) {
        int length0 = 0;
        int length1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                length0++;
            } else {
                length1++;
            }
        }
        return new int[]{length0 , length1};
    }

}
