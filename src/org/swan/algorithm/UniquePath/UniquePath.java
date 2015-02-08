package org.swan.algorithm.UniquePath;

public class UniquePath {
	public static void main(String[] args){
		Solution sol = new Solution();
		sol.uniquePaths(2, 2);
	}
}

class Solution{
	public int uniquePaths(int m, int n){
		int[][] A=  new int[m][n];
		for(int i = 0; i < m; i++){
			A[i][0] = 1;
		}
		for(int i = 0; i < n; i++){
			A[0][i] = 1;
		}
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				A[i][j] = A[i - 1][j] + A[i][j - 1];
			}
		}
		return A[m - 1][n - 1];
	}
}

class SolutionII {
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0){
            return 0;
        }
        return combine(m+n-2, (m<n)?m - 1:n - 1);
    }
    
    int combine(int n, int k){
        if(k == 0 || n == k){
            return 1;
        }
    	int[][] record = new int[n + 1][k + 1];
    	for(int i = 0; i <= k; i++){
    		record[i][0] = 1;
    		record[i][i] = 1;
    	}
    	for(int i = k + 1; i <= n; i++){
    		record[i][0] = 1;
    	}
    	for(int row = 2; row <= n; row++){
    		for(int col = 1; col <= k; col++){
    			record[row][col] = record[row - 1][col - 1] + record[row - 1][col];
    		}
    	}
    	return record[n][k];
    }
}