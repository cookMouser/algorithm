package shuati.back.lc79;

/**
 * LC: 79 Word Search
 */
class Solution {

    private boolean[][] visited;

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

        boolean existed = false;
        visited[boardX][boardY] = true;
        // 上面元素
        if (boardX - 1 >= 0 && !visited[boardX -1][boardY]) {
            existed = back(board, boardX - 1, boardY, word, wordIndex + 1);
        }

        // 右边元素
        if (!existed) {
            if (boardY + 1 < board[boardX].length && !visited[boardX][boardY + 1]) {
                existed = back(board, boardX, boardY + 1, word, wordIndex + 1);
            }
        }

        // 下面元素
        if (!existed) {
            if (boardX + 1 < board.length && !visited[boardX + 1][boardY]) {
                existed = back(board, boardX + 1, boardY, word, wordIndex + 1);
            }
        }

        // 左边元素
        if (!existed) {
            if (boardY - 1 >= 0 && !visited[boardX][boardY - 1]) {
                existed = back(board, boardX, boardY - 1, word, wordIndex + 1);
            }
        }

        visited[boardX][boardY] = false;
        return existed;
    }
}
