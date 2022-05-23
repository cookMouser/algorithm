package shuati.trackBack;

public class lc79 {

    private int[][] changes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 千万注意这里不能设置状态，否则要手动去清理一次状态
//                visited[x][y] = false;
                if (backTrack(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean backTrack(char[][] board, String word, boolean[][] visited, int x, int y, int index) {
        if (index == word.length() - 1) {
            if (board[x][y] == word.charAt(index)) {
                return true;
            } else {
                return false;
            }
        }
        if (board[x][y] != word.charAt(index)) {
            return false;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int newX = x + changes[i][0];
            int newY = y + changes[i][1];
            // 注意边界问题；注意先判断边界在判断visited
            if (inArea(board, newX, newY) && !visited[newX][newY]) {
                if (backTrack(board, word, visited, newX, newY, index + 1)) {
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }

    private boolean inArea(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}
