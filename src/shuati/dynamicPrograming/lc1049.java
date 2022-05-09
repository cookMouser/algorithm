package shuati.dynamicPrograming;

public class lc1049 {

    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        if (n == 1) {
            return stones[0];
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stones[i];
        }
        int capacity = sum / 2;

        int[] dp = new int[capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            if (stones[0] <= i) {
                dp[i] = stones[0];
            } else {
                dp[i] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        int half = dp[capacity];
        return Math.abs(half - (sum - half));
    }
}
