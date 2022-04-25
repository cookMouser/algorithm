package shuati.dynamic.lc70;

class Solution {
    private int[] count;
    public int climbStairs(int n) {
        count = new int[n + 1];
        climbStairs(n);
        return count[n];
    }

    private int climb(int target) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        if (count[target] > 0) {
            return count[target];
        }
        count[target] = climb(target - 1) + climb(target - 2);
        return count[target];
    }
}
