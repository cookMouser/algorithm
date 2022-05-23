package shuati.dynamicPrograming;

public class lc1035 {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        // dp[i][j]表示text1[0, i - 1]、text2[0, j - 1]的两个子串的最长公共子序列
        int[][] dp = new int[n1 + 1][n2 + 1];

        int res = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
