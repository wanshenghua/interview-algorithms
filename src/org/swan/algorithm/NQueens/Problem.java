package org.swan.algorithm.NQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard such that no two queens attack each other.
	Given an integer n, return all distinct solutions to the n-queens puzzle.
 * @author SHENGHUA
 *
 */

public class Problem {
	public static void main(String[] args) {
		Solution sol = new Solution();
		List<String[]> res = sol.solveNQueens(4);
		
		for(String[] oneSolution : res) {
			System.out.println();
			for(int i = 0; i < oneSolution.length; i++){
				System.out.println(oneSolution[i]);
			}
		}
		System.out.println("End");
	}
}

class Solution {
	private List<int[]> solutions = new ArrayList<>();
	private List<String[]> stringFormatSolutions = new ArrayList<>();
	
	public List<String[]> solveNQueens(int n) {
        stringFormatSolutions.clear();
		solutions.clear();
        
		int[] colPos = new int[n];
		Arrays.fill(colPos, -1);
        solveNQueensAtRow(colPos, 0);
        convertSolutions(n);
        
        return stringFormatSolutions;
    }
	
	private void convertSolutions(int n){
		for(int[] sol : solutions) {
			String[] strSol = convertSolution(sol);
			stringFormatSolutions.add(strSol);
		}
	}
	
	private String[] convertSolution(int[] sol){
		String[] lines = new String[sol.length];
		for(int i = 0; i < sol.length; i++){
			lines[i] = convertRow(sol[i], sol.length);
		}
		return lines;
	}
	
	private String convertRow(int pos, int n){
		StringBuilder line = new StringBuilder();
		for(int i = 0; i < n; i++){
			if(i == pos) {
				line.append('Q');
			}else{
				line.append('.');
			}
		}
		return line.toString();
	}
    
    private void solveNQueensAtRow(int[] colPos, int row) {
    	if(row == colPos.length){	
    		solutions.add(Arrays.copyOf(colPos, colPos.length));      		
    		return;
    	}
    	for(int col = 0; col < colPos.length; col++){
    		if(checkRow(colPos, row, col) && checkColumn(colPos, row, col) && checkDiagonal(colPos, row, col)) {
    			colPos[row] = col;
    			solveNQueensAtRow(colPos, row + 1);
    		}
    	}
    }
    
    private boolean checkRow(int[] colPos, int row, int col){
    	//no same row conflicts
    	return true;
    }
    
    private boolean checkColumn(int[] colPos, int row, int col){
    	//not in same column with previously place queens
    	for(int i = 0; i < row; i++){
    		if(colPos[i] == col){
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean checkDiagonal(int[] colPos, int row, int col){
    	//not in same diagonal with previously place queens
    	for(int i = 0; i < row; i++){
    		int j = colPos[i];
    		if( (row - i == col - j) || (row - i == j - col)){
    			return false;
    		}
    	}
    	return true;
    }
}