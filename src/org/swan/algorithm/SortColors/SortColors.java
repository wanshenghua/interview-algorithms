package org.swan.algorithm.SortColors;

public class SortColors {

}

class Solution {
    public void sortColors(int[] A) {
    	int k = 3;
    	int[] num = new int[k];
    	
    	for(int v : A){
    		num[v]++;
    	}
    	
    	int count = 0;
    	for(int i = 0; i < k; i++){
    		for(int j = 0; j < num[i]; j++){
    			A[count + j] = i;
    		}
    		count += num[i];
    	}
    }
}

class SolutionII {
    public void sortColors(int[] A) {
    	int index0 = 0;
    	int index2 = A.length - 1;
    	for(int i = 0; i <= index2; i++){
    		if(A[i] == 0){
    			//swap [i], [index0]
    			int tmp = A[i];
    			A[i] = A[index0];
    			A[index0] = tmp;
    			index0++;
    		}else if(A[i] == 2){
    			int tmp = A[i];
    			A[i] = A[index2];
    			A[index2] = tmp;
    			index2--;
    		}
    		i++;
    	}
    }
}