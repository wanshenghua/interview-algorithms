package org.swan.algorithm.SurroundedRegions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class SurroundedRegions {
	public static void main(String[] args){
		char[][] board= {new char[]{'X', 'O', 'X', 'O', 'X', 'O'}, 
				 			new char[]{'O', 'X', 'O', 'X', 'O', 'X' },
				 				new char[]{'X', 'O', 'X', 'O', 'X', 'O'},
				 					new char[]{'O', 'X', 'O', 'X', 'O', 'X'}};
		SolutionIterativeDFS sol = new SolutionIterativeDFS();
		sol.solve(board);
	}
}

// DFS doesnot work for big data
class SolutionDFS {
	public void solve(char[][] board) {
		if (board.length <= 2) {
			return;
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == 'O') {
				dfs(board, i, 0);
			}
			if (board[i][board[0].length - 1] == 'O') {
				dfs(board, i, board.length - 1);
			}
		}
		for (int j = 0; j < board[0].length; j++) {
			if (board[0][j] == 'O') {
				dfs(board, 0, j);
			}
			if (board[board.length - 1][j] == 'O') {
				dfs(board, board.length - 1, j);
			}
		}
		// set O to X
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}

	private void dfs(char[][] board, int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return;
		}
		if (board[row][col] != 'O') {
			return;
		}
		board[row][col] = '#';
		dfs(board, row - 1, col);
		dfs(board, row + 1, col);
		dfs(board, row, col - 1);
		dfs(board, row, col + 1);
	}
}

class SolutionIterativeDFS {
	public void solve(char[][] board) {
		if (board.length <= 2) {
			return;
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == 'O') {
				dfs(board, i, 0);
			}
			if (board[i][board[0].length - 1] == 'O') {
				dfs(board, i, board[0].length - 1);
			}
		}
		for (int j = 0; j < board[0].length; j++) {
			if (board[0][j] == 'O') {
				dfs(board, 0, j);
			}
			if (board[board.length - 1][j] == 'O') {
				dfs(board, board.length - 1, j);
			}
		}
		// set O to X
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}

	private void dfs(char[][] board, int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return;
		}
		if (board[row][col] != 'O') {
			return;
		}
		Stack<ArrayList<Integer>> stack = new Stack<>();
		ArrayList<Integer> pair = new ArrayList<>(2);
		pair.add(row);
		pair.add(col);
		stack.push(pair);

		while (!stack.empty()) {
			ArrayList<Integer> p = stack.pop();

			int i = p.get(0);
			int j = p.get(1);

			if (i < 0 || i >= board.length || j < 0
					|| j >= board[0].length) {
				continue;
			}
			if (board[i][j] != 'O') {
				continue;
			}
			board[i][j] = '#';

			ArrayList<Integer> left = new ArrayList<>(2);
			left.add(i - 1);
			left.add(j);

			ArrayList<Integer> right = new ArrayList<>(2);
			right.add(i + 1);
			right.add(j);

			ArrayList<Integer> up = new ArrayList<>(2);
			up.add(i);
			up.add(j + 1);

			ArrayList<Integer> down = new ArrayList<>(2);
			down.add(i);
			down.add(j - 1);

			stack.push(left);
			stack.push(right);
			stack.push(up);
			stack.push(down);
		}
	}
}
