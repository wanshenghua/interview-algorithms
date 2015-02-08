package org.swan.algorithm.PalindromePartitioningII;

import java.util.HashMap;

/**
 * Given a string s, partition s such that 
 * every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",Return 1 since the palindrome 
 * partitioning ["aa","b"] could be produced using 1 cut.
 * @author SHENGHUA
 *
 */
public class PalindromePartitioningII {
	public static void main(String[] args){
		Solution sol = new Solution();
		System.out.println(sol.minCut("a"));
		System.out.println(sol.minCut("ab"));
		System.out.println(sol.minCut("aab"));
		System.out.println(sol.minCut("bb"));
		System.out.println(sol.minCut("bbbb"));
	}
}


class Solution {
    public int minCut(String s) {
    	int len = s.length();
    	if(s == null || len == 0){
    		return 0;
    	}
        boolean[][] matrix = new boolean[len][len]; 
        int[] cuts = new int[len];
        
        for(int i = 0; i < len; i++){
        	cuts[i] = i;//upper bound
        }

        for(int i = 0; i < len; i++){
        	for(int j = 0; j <= i; j++){
           		if( (s.charAt(j) == s.charAt(i) && i - j < 2) || (s.charAt(j) == s.charAt(i) && matrix[j+1][i-1])){
           			matrix[j][i] = true;
            	}        			
        	}
        }
        for(int i = 0; i < len; i++){
        	for(int j = 1; j <= i; j++){
        		if(matrix[j][i]){
        			cuts[i] = Math.min(cuts[i], cuts[j - 1] + 1);
        		}
        	}
        	if(matrix[0][i]){
        		cuts[i] = 0;
        	}
        }
        return cuts[len - 1];
    }
}