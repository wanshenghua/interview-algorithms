package org.swan.algorithm.SudokuSolver;

public class SudokuSolver {

}

class Solution {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		solved(board);
	}

	private boolean solved(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {
						if (isValidSudoku(board, i, j, c)) {
							board[i][j] = c;
							if (solved(board)) {
								return true;
							} else {
								board[i][j] = '.';
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidSudoku(char[][] board, int row, int col, int c) {

		for (int i = 0; i < 9; i++) {
			if (board[row][i] == c) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == c) {
				return false;
			}

		}
		int base_row = row / 3 * 3;
		int base_col = col / 3 * 3;
		for (int s = 0; s < 3; s++) {
			for (int t = 0; t < 3; t++) {
				if (board[base_row + s][base_col + t] == c) {
					return false;
				}
			}
		}
		return true;
	}
}
