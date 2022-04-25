package shuati.back.lc79;

/**
 * LC: 79 Word Search
 * 代码级别的优化
 */
class Solution2 {

    private boolean[][] visited;
    // 某个元素上右下左四个位置相对的索引差值
    private int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (back(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean back(char[][] board, int boardX, int boardY, String word, int wordIndex) {
        if (!(board[boardX][boardY] == word.charAt(wordIndex))) {
            return false;
        }

        if (wordIndex == word.length() - 1) {
            return true;
        }

        visited[boardX][boardY] = true;
        for (int i = 0; i < offset.length; i++) {
            int newX = boardX + offset[i][0];
            int newY = boardY + offset[i][1];
            // 千万注意这两个条件判断的先后顺序，如果不在area内，那么visited会报数组越界异常
            if (inArea(board, newX, newY) && !visited[newX][newY]) {
                if (back(board, newX, newY, word, wordIndex + 1)) {
                    return true;
                }
            }
        }
        visited[boardX][boardY] = false;
        return false;
    }

    // 当前坐标是否是有效的
    private boolean inArea(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[x].length;
    }
}
