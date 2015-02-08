package org.swan.algorithm.SetMatrixZeroes;

public class SetMatrixZeroes {

}

class Solution {
    public void setZeroes(int[][] matrix) {
    	if(matrix == null){
    		return;
    	}
    	boolean isFirstRowZero = false;
    	boolean isFirstColumnZero = false;
    	
    	int m = matrix.length;
    	int n = matrix[0].length;
    	//check zero-status of first row/column
    	for(int i = 0; i < m; i++){
    		if(matrix[i][0] == 0){
    			isFirstColumnZero = true;
    			break;
    		}
    	}
    	for(int i = 0; i < n; i++){
    		if(matrix[0][i] == 0){
    			isFirstRowZero = true;
    			break;
    		}
    	}
    	
    	//use 1st row/column as flags
    	for(int i = 1; i < m; i++){
    		for(int j = 1; j < n; j++){
    			if(matrix[i][j] == 0){
    				matrix[i][0] = 0;
    				matrix[0][j] = 0;
    			}
    		}
    	}
    	//clear submatrix according to flags
    	for(int i = 1; i < n; i++){
    		if(matrix[0][i] == 0){
    			for(int j = 1; j < m;j++){
    				matrix[j][i] = 0;
    			}
    		}
    	}
    	for(int i = 1; i < m; i++){
    		if(matrix[i][0] == 0){
    			for(int j = 1; j < n; j++){
    				matrix[i][j] = 0;
    			}
    		}
    	}
    	//set 1st row/column to zeros according to previous flags
    	if(isFirstRowZero){
    		for(int i = 0; i < n; i++){
    			matrix[0][i] = 0;
    		}
    	}
    	if(isFirstColumnZero){
    		for(int i = 0; i < m; i++){
    			matrix[i][0] = 0;
    		}
    	}
    }
}