package shuati.dynamic.lc70;

class Solution2 {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int[] count = new int[n + 1];
        count[1] = 1;
        count[2] = 2;
        for (int i = 3; i < count.length; i++) {
            count[i] = count[i - 1] + count[i - 2];
        }
        return count[n];
    }
}
