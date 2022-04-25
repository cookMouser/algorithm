package shuati.back.lc37;

/**
 * LC: 37. Sudoku solver
 */
class Solution {
    // 某行有没有某个数字
    private boolean[][] rowArr = new boolean[9][9];

    // 某一列有没有某个数字
    private boolean[][] colArr = new boolean[9][9];

    // 某个小宫格有没有某个数字
    private boolean[][][] cubeArr = new boolean[3][3][9];

    public void solveSudoku(char[][] board) {
        // 先处理已填充的数据，可以剪枝
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int e = board[i][j] - '0';
                    rowArr[i][e - 1] = true;
                    colArr[j][e - 1] = true;
                    cubeArr[i/3][j/3][e - 1] = true;
                }
            }
        }
        back(board, 0, 0);
    }

    /**
     * 超暴力求解，一个一个位置进行递归
     * @param board
     * @param row 当前需要填充的行
     * @param col 当前需要填充的列
     */
    private boolean back(char[][] board, int row, int col) {
        // 说明遍历了所有元素都可以填充进去
        if (row == 9) {
            return true;
        }

        // 转到下一行
        if (col == 9) {
            return back(board, row + 1, 0);
        }

        // 不需要填充的位置直接跳过
        if (board[row][col] != '.') {
            return back(board, row, col + 1);
        }

        // 填充1~9的数
        for (int i = 1; i <= 9; i++) {
            if ((!rowArr[row][i - 1]) && (!colArr[col][i - 1]) && (!cubeArr[row/3][col/3][i - 1])) {
                rowArr[row][i - 1] = true;
                colArr[col][i - 1] = true;
                cubeArr[row/3][col/3][i - 1] = true;
                // 注意字符和数字的区别
                board[row][col] = (char) ('0' + i);
                if (!back(board, row, col + 1)) {
                    // 如果失败了则需要回溯已改变的状态，如果成功了则不需要回溯，保持已填充的值
                    rowArr[row][i - 1] = false;
                    colArr[col][i - 1] = false;
                    cubeArr[row/3][col/3][i - 1] = false;
                    board[row][col] = '.';
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
