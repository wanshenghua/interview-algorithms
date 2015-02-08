package org.swan.algorithm.RotateImage;

public class RotateImage {
	public static void main(String[] arg){
		int[][] matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[1][0] = 3;
		matrix[1][1] = 4;	
//		int[][] matrix = new int[4][4];
//		int counter = 1;
//		for(int i = 0; i < 4; i++)
//			for(int j = 0; j < 4; j++){
//				matrix[i][j] = counter++;
//			}
		Solution sol = new Solution();
		sol.rotate(matrix);
		int a = 1+1;
	}
}

class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
        	return;
        }
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++){
        	for(int j = i; j < n - i - 1; j++){
        		int tmp = matrix[i][j];
        		matrix[i][j] = matrix[n - j - 1][i];
        		matrix[n - j - 1][i] = matrix[n - i - 1][n -j - 1];
        		matrix[n - i - 1][n -j - 1] = matrix[j][n - i -1];
        		matrix[j][n - i -1] = tmp;
        	}
        }
    }
}