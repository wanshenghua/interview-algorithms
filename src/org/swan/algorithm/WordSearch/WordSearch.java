package org.swan.algorithm.WordSearch;

public class WordSearch {
	public static void main(String[] args){
		Solution sol = new Solution();
		System.out.println(sol.exist(new char[][]{new char[]{'a', 'b'}}, "ba"));
	}
}

class Solution {
    public boolean exist(char[][] board, String word) {
    	if(board == null){
    		return false;
    	}
    	
		boolean[][] visited = new boolean[board.length][board[0].length];
    	for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
    			if(searchWord(visited, board, 0, 0, word, 0)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private boolean searchWord(boolean[][] visited, char[][] board, int row, int col, String word, int index){
		if(index == word.length()){
			return true;
		}
		if(row < 0 || row >= board.length || col < 0 || col >= board[0].length){
			return false;
		}
		
		if(!visited[row][col] && board[row][col] == word.charAt(index)){
			visited[row][col] = true;
			if(searchWord(visited, board, row - 1, col, word, index + 1) ||
					searchWord(visited, board, row + 1, col, word, index + 1) ||
					searchWord(visited, board, row, col - 1, word, index + 1) ||
					searchWord(visited, board, row, col + 1, word, index + 1) ){
				return true;
			}
			visited[row][col] = false;
			
		}

    	return false;
    }
}
