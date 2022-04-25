package shuati.dynamic.lc279;


/**
 * LC: 279. Perfect Squares
 * 回溯
 * 过不了lc，显示超出时间限制
 */
class Solution2 {
    public int numSquares(int n) {
        Integer[] arr = new Integer[n + 1];
        return numSquares(n, arr);
    }

    private int numSquares(int n, Integer[] arr) {
        if (n == 0) {
            return 0;
        }

        if (arr[n] == null) {
            arr[n] = n + 1;
            int i = 1;
            while (i*i <= n) {
                arr[n] = Math.min(arr[n], numSquares(n - i*i) + 1);
                i++;
            }
        }
        return arr[n];
    }

}
