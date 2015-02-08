package org.swan.algorithm.SearchInsertPosition;

public class SearchInsertPosition {

}

class Solution {
    public int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0){
        	return 0;
        }
        return helper(A, 0, A.length - 1, target);
    }
    
    int helper(int[] A, int beg, int end, int target){
    	if(beg > end){
    		return beg;
    	}
    	int mid = (beg + end) / 2;
    	if(A[mid] == target){
    		return mid;
    	}else if(A[mid] < target){
    		return helper(A, mid + 1, end, target);
    	}else{
    		return helper(A, beg, mid - 1, target);
    	}
    }
}