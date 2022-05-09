package shuati.dynamicPrograming;

public class lc322 {

    public int coinChange(int[] coins, int amount) {

        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[i] = i / coins[0];
            } else {
                // 这里千万要注意不能使用0。取得这个值要体现出两点特点：
                // 1、是否能够刚好凑满
                // 2、凑满的话最小的数目是多少
                // 如果不能凑满给设置为0，首先语义上0表示取0个数可以凑满（即amount为0的时候），其次后面直接取较小值函数，则会得到结果0，不对
                dp[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }


}
