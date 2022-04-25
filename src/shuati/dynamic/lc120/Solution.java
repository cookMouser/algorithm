package shuati.dynamic.lc120;

import java.util.List;

/**
 * LC: 120. Triangle
 * 从顶向下递归，记忆化进行优化
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 不直接使用int[][]，而是用包装类，那么初始默认值为null。很容易分辨出来有没有设置过值
        Integer[][] arr = new Integer[triangle.size()][triangle.size()];
        return mimimum(triangle, 0, 0, arr);
    }

    private int mimimum(List<List<Integer>> triangle, int x, int y, Integer[][] arr) {
        if (x == triangle.size() - 1) {
            return triangle.get(x).get(y);
        }

        if (arr[x][y] != null) {
            return arr[x][y];
        }

        arr[x][y] = triangle.get(x).get(y) + Math.min(mimimum(triangle, x + 1, y, arr), mimimum(triangle, x + 1, y + 1, arr));
        return arr[x][y];
    }
}
