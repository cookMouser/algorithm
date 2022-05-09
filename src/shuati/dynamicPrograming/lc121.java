package shuati.dynamicPrograming;


import java.util.PriorityQueue;

public class lc121 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0;
        for (int i = 1; i < n; i++) {
            pq.add(prices[i - 1]);
            if (prices[i] - pq.peek() > res) {
                res = prices[i] - pq.peek();
            }
        }
        return res;
    }
}
