package shuati.dynamic.lc343;

/**
 * LC: 343. Integer Break
 * 用递归来写
 */
class Solution2 {
    public int integerBreak(int n) {
        assert (n >= 2);
        Integer[] arr = new Integer[n + 1];
        return integerBreak(n, arr);
    }
    //递推表达式 f(n) = Math.max((n -1) * f(1), (n -1) * 1, (n -2) * f(2), (n -2) * 2... 1 * f(n - 1), 1 * (n - 1))
    private int integerBreak(int n, Integer[] arr) {
        if (n == 1) {
            return 1;
        }

        if (arr[n] != null) {
            return arr[n];
        }
        arr[n] = -1;
        for (int i = 1; i < n; i++) {
            arr[n] = Math.max(integerBreak(i, arr) * (n - i), arr[n]);
            arr[n] = Math.max(i * (n - i), arr[n]);
        }
        return arr[n];
    }
}
