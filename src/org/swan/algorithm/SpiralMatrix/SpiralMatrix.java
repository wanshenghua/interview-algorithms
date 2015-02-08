package org.swan.algorithm.SpiralMatrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

}


class SolutionSpiralMatrixI {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0){
        	return res;
        }
        helper(res, matrix, 0, matrix.length, 0, matrix[0].length);
        return res;
    }
    
    private void helper(List<Integer> res, int[][] m, 
    		int rBeg, int rEnd, int cBeg, int cEnd){
    	if(rBeg >= rEnd || cBeg >= cEnd){
    		return;
    	}
    	if(rBeg + 1 == rEnd){
        	for(int i = cBeg; i < cEnd; i++){
        		res.add(m[rBeg][i]);
        	}
        	return;
    	}
    	if(cBeg + 1 == cEnd){
        	for(int i = rBeg; i < rEnd; i++){
        		res.add(m[i][cEnd - 1]);
        	}   
        	return;
    	}
    	
    	for(int i = cBeg; i < cEnd - 1; i++){
    		res.add(m[rBeg][i]);
    	}
    	for(int i = rBeg; i < rEnd - 1; i++){
    		res.add(m[i][cEnd - 1]);
    	}
    	for(int i = cEnd - 1; i > cBeg; i--){
    		res.add(m[rEnd - 1][i]);
    	}
    	for(int i = rEnd - 1; i > rBeg; i--){
    		res.add(m[i][cBeg]);
    	}
    	
    	helper(res, m, rBeg + 1, rEnd - 1, cBeg + 1, cEnd - 1);
    }
}
