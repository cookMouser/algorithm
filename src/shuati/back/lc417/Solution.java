package shuati.back.lc417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC: 417. Pacific Atlantic Water Flow
 * 与邻边相关，遍历邻边更好处理
 */
class Solution {
    private boolean[][] pacific;
    private boolean[][] atlantic;
    private int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        pacific = new boolean[m][n];
        atlantic = new boolean[m][n];
        // pacific
        for (int i = 0; i < m; i++) {
            back(heights, i, 0, pacific);
        }
        for (int i = 0; i < n; i++) {
            back(heights, 0, i, pacific);
        }

        // atlantic
        for (int i = 0; i < m; i++) {
            back(heights, i, n - 1, atlantic);
        }
        for (int i = 0; i < n; i++) {
            back(heights, m - 1, i, atlantic);
        }

        // 遍历找出满足两个条件的数据
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void back(int[][] heights, int startX, int startY, boolean[][] visited) {
        visited[startX][startY] = true;
        for (int i = 0; i < offset.length; i++) {
            int newX = startX + offset[i][0];
            int newY = startY + offset[i][1];
            if (inArea(heights, newX, newY) && !visited[newX][newY] && heights[newX][newY] >= heights[startX][startY]) {
                back(heights, newX, newY, visited);
            }
        }
    }

    private boolean inArea(int[][] heights, int x, int y) {
        return x >= 0 && x < heights.length && y >= 0 && y < heights[x].length;
    }
}
