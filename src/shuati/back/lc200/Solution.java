package shuati.back.lc200;

class Solution {
    private int ccount = 0;
    private boolean[][] visited;
    private int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    back(grid, i, j);
                    ccount++;
                }
            }
        }
        return ccount;
    }

    private void back(char[][] grid, int startX, int startY) {
        // 终止条件：碰到了0；一个元素的四周都是0
        if (grid[startX][startY] != '1') {
            return;
        }
        visited[startX][startY] = true;
        for (int i = 0; i < offset.length; i++) {
            int newX = startX + offset[i][0];
            int newY = startY + offset[i][1];
            if (inArea(grid, newX, newY) && !visited[newX][newY]) {
                back(grid, newX, newY);
            }
        }

    }

    private boolean inArea(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[x].length;
    }
}
