package org.swan.algorithm.ValidSudoku;

public class ValidSudoku {

}

class Solution {
    public boolean isValidSudoku(char[][] board) {
    	int m = board.length;
    	int n = board[0].length;
    	//by row
        for(int i = 0; i < m; i++){
        	boolean[] flag = new boolean[10];
        	for(int j = 0; j < n; j++){
        		if(board[i][j] != '.'){
        		    int v = board[i][j] - '0';
        			if(flag[v]){
        				return false;
        			}else{
        				flag[v] = true;
        			}
        		}
        	}
        }
        //by column
        for(int i = 0; i < n;i++){
        	boolean[] flag = new boolean[10];
        	for(int j = 0; j < m; j++){
        		if(board[j][i] != '.'){
         		    int v = board[j][i] - '0';
        			if(flag[v]){
        				return false;
        			}else{
        				flag[v] = true;
        			}
        		}
        	}
        }
        //by block
        for(int i = 0; i < m; i+=3){
        	for(int j = 0; j < n; j+=3){
        		boolean[] flag = new boolean[10];
        		for(int s = 0; s < 3; s++){
        			for(int t = 0; t < 3; t++){
                		if(board[i+s][j+t] != '.'){
                		    int v = board[i+s][j+t] - '0';
                			if(flag[v]){
                				return false;
                			}else{
                				flag[v] = true;
                			}
                		}
        			}
        		}
        	}
        }
        return true;
    }
}