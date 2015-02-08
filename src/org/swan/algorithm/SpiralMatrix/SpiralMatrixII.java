package org.swan.algorithm.SpiralMatrix;

public class SpiralMatrixII {
	public static void main(String[] args){
		SolutionII sol = new SolutionII();
		int[][] mat = sol.generateMatrix(3);
		for(int[] i : mat){
			for(int j : i){
				System.out.print(Integer.toString(j) + ' ');
			}
			System.out.println();
		}
	}
}


class SolutionII {
    public int[][] generateMatrix(int n) {
    	int[][] matrix = new int[n][n];
    	generateMatrix(matrix, 1, 0, n);
    	if(n % 2 == 1) {
    		int mid = n/2;
    		matrix[mid][mid] = n * n;
    	}
    	return matrix;
    }
    
    void generateMatrix(int[][] mat, int startNumber, int layer, int n){
  	   	if(layer == (n + 1) / 2){
    		return;
    	}
    	int beg = layer;
    	int last = n - 1 - layer;
    	
    	for(int i = beg; i < last; i++){
    		mat[beg][i] = startNumber++;
    	}
    	for(int i = beg; i < last; i++){
    		mat[i][last] = startNumber++;
    	}
    	for(int i = last ; i > beg; i--){
    		mat[last][i] = startNumber++;
    	}
    	for(int i = last; i > beg; i--){
    		mat[i][beg] = startNumber++;
    	}
    	generateMatrix(mat, startNumber, layer+1, n);
    }
}