package shuati.back.lc130;

/**
 * LC: 130 Surrounded Regions
 */
class Solution {

    private boolean[][] visited;
    // 某个元素上右下左四个位置相对的索引差值
    private int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];

        // 遍历四周边上的'O',
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O' && !visited[0][i]) {
                back(board, 0, i);
            }
            if (board[m - 1][i] == 'O' && !visited[m - 1][i]) {
                back(board, m - 1, i);
            }
        }

        for (int i = 1; i < m - 1; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                back(board, i, 0);
            }
            if (board[i][n - 1] == 'O' && !visited[i][n - 1]) {
                back(board, i, n - 1);
            }
        }

        // 没有被标记的'O'，替换为'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

    }


    private void back(char[][] board, int startX, int startY) {
        if (board[startX][startY] != 'O') {
            return;
        }
        visited[startX][startY] = true;
        for (int i = 0; i < offset.length; i++) {
            int newX = startX + offset[i][0];
            int newY = startY + offset[i][1];
            if (inArea(board, newX, newY) && !visited[newX][newY]) {
                back(board, newX, newY);
            }
        }

    }

    private boolean inArea(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[x].length;
    }
}
