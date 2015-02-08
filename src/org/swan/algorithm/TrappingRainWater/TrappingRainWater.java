package org.swan.algorithm.TrappingRainWater;

public class TrappingRainWater {

}

class Solution {
    public int trap(int[] A) {
    	if(A.length < 3){
    		return 0;
    	}
    	int[] leftHighestElevation = new int[A.length];
    	int[] rightHighestElevation = new int[A.length];

    	int max = A[0];
    	for(int i=1; i <= A.length - 2; i++){
    		leftHighestElevation[i] = max;
    		if(max < A[i]){
    			max = A[i];
    		}
    	}
    	max = A[A.length - 1];
    	for(int i = A.length - 2; i >= 1; i--){
    		rightHighestElevation[i] = max;
    		if(max < A[i]){
    			max = A[i];
    		}
    	}

    	//sum contributions    	
    	int sum = 0;
    	for(int i = 1; i <= A.length - 2; i++){
    	    if(Math.min(leftHighestElevation[i], rightHighestElevation[i]) > A[i])
    		    sum += Math.min(leftHighestElevation[i], rightHighestElevation[i]) - A[i];
    	}
    	
    	return sum;
    }
}