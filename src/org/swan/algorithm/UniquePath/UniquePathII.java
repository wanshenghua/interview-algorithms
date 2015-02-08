package org.swan.algorithm.UniquePath;

public class UniquePathII {

}

class SolutionUniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
        int[][] A = new int[m][n];
        if(obstacleGrid[0][0] == 0){
            A[0][0] = 1;
        }
     
        boolean blocked = false;
        for(int i = 1; i < n; i++){
        	if(!blocked){
        		if(obstacleGrid[0][i-1] == 0){
        			if(obstacleGrid[0][i] == 0){
        			    A[0][i] = 1;
        			}
        		}else{
        			blocked = true;
        		}
        	}
        }
        blocked = false;
        for(int i = 1; i < m; i++){
        	if(!blocked){
        		if(obstacleGrid[i-1][0] == 0){
        		    if(obstacleGrid[i][0] == 0) {
        			    A[i][0] = 1;
        		    }
        		}else{
        			blocked = true;
        		}
        	}
        }
        for(int i = 1; i < m; i++){
        	for(int j = 1; j < n; j++){
        		if(obstacleGrid[i][j] == 0){
        			if(obstacleGrid[i][j-1] == 0){
        				A[i][j] += A[i][j - 1];
        			}
        			if(obstacleGrid[i-1][j] == 0){
        				A[i][j] += A[i - 1][j];
        			}
        		}else{
        			A[i][j] = 0;
        		}
        	}
        }
        return A[m - 1][n - 1];
    }
}
