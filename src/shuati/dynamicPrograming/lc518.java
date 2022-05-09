package shuati.dynamicPrograming;

public class lc518 {

    public int change(int amount, int[] coins) {
        int n = coins.length;

        // 表示可以装满背包的元素种类的组合
        int[] dp = new int[amount + 1];

        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[i] = 1;
            } else {
                dp[i] = 0;
            }
        }

        for (int i = 1; i < n; i ++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];

    }
}
