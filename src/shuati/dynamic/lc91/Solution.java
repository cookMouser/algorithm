package shuati.dynamic.lc91;

/**
 * LC: 91. Decode Ways
 * 在本题中忽略了一些特殊场景，默认给的字符串时合法的。没有连续的0，没有负数
 */
class Solution {
    /**
     * f(n) =
     *      1、s.charAt(0) == ‘0’ => 0
     *      2、(s.charAt(n + 1) - '0') + (s.charAt(n) - '0') * 10 <= 26  => f(n + 1) + f(n + 2)
     *         (s.charAt(n + 1) - '0') + (s.charAt(n) - '0') * 10 > 26  => f(n + 1)
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        // 表示字符串索引为n的字符，能够得到的最大解码总数
        int[] dp = new int[s.length() + 1];
        // 主要用于求解dp[s.length() - 2],字符串的最后两个值直接可以切割为一块
        dp[s.length()] = 1;
        // 千万注意次数对n - 1 初始化数据的处理
        if (s.charAt(s.length() - 1) == '0') {
            dp[s.length() - 1] = 0;
        } else {
            dp[s.length() - 1] = 1;
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }

            if ((s.charAt(i + 1) - '0') + (s.charAt(i) - '0') * 10 <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

}
