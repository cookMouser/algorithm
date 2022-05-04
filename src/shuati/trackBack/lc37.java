package shuati.trackBack;

public class lc37 {

    private boolean[][] rows;
    private boolean[][] cols;
    private boolean[][][] cubes;

    public void solveSudoku(char[][] board) {

        rows = new boolean[9][9];
        cols = new boolean[9][9];
        cubes = new boolean[3][3][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j]  != '.') {
                    int num = board[i][j] - '0';
                    rows[i][num - 1] = true;
                    cols[j][num - 1] = true;
                    cubes[i / 3][j / 3][num - 1] = true;
                }
            }
        }
        backTrack(board, 0, 0);

    }

    private boolean backTrack(char[][] board, int row, int col) {
        if (row > 8) {
            return true;
        }

        // 控制换行
        if (col > 8) {
            return backTrack(board, row + 1, 0);
        }

        if (board[row][col] != '.') {
            // visited已经处理过了
            return backTrack(board, row, col + 1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = (char) ('0' + num);
                rows[row][num - 1] = true;
                cols[col][num - 1] = true;
                cubes[row / 3][col / 3][num - 1] = true;
                if (backTrack(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
                rows[row][num - 1] = false;
                cols[col][num - 1] = false;
                cubes[row / 3][col / 3][num - 1] = false;
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int num) {
        return (!rows[row][num - 1]) && (!cols[col][num - 1]) && (!cubes[row / 3][col / 3][num - 1]);
    }
}
