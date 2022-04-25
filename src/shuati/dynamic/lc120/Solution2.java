package shuati.dynamic.lc120;

import java.util.List;

/**
 * LC: 120. Triangle
 * 使用动态规划
 */
class Solution2 {
    public int minimumTotal(List<List<Integer>> triangle) {
       Integer[][] dp = new Integer[triangle.size()][triangle.size()];
       for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
           dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
       }
       for (int i = triangle.size() - 2; i >= 0; i--) {
           for (int j = 0; j < triangle.get(i).size(); j++) {
               dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
           }
       }
       return dp[0][0];
    }

}
