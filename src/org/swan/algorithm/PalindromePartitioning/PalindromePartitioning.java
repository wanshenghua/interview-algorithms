package org.swan.algorithm.PalindromePartitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * Given a string s, partition s such that every 
 * substring of the partition is a palindrome. 
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab", Return
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * 
 * @author SHENGHUA
 *
 */
public class PalindromePartitioning {
	public static void main(String[] args){
		Solution sol = new Solution();
		List<List<String>> res = sol.partition("aaa");
		
		for(List<String> l : res){
			for(String s : l){
				System.out.printf("%s ", s);
			}
			System.out.println();
		}
	}
}

class Solution {
    public List<List<String>> partition(String s) {
        return generatePartition(s, 0, s.length());
    }
    
    List<List<String>> generatePartition(String s, int beg, int end) {
    	if(s.length() == 0) {
    		List<String> array = new ArrayList<>();
    		List<List<String>> aa = new ArrayList<>();
    		aa.add(array);
    		return aa;
    	}
    	ArrayList<List<String>> partitionsBeg2End = new ArrayList<>();
    	for(int i = beg; i < end; i++){
    		String prefix = s.substring(beg, i + 1);
    		String suffix = s.substring(i + 1, end);
    		if(isPalindrome(prefix)){
    			List<List<String>> partitionsI2End = generatePartition(suffix, 0, suffix.length());
    			if(partitionsI2End.size() > 0 || suffix.length() == 0){
    				for(int j = 0; j < partitionsI2End.size(); j++){
    					partitionsI2End.get(j).add(0, prefix);
    				}
    			}
    			partitionsBeg2End.addAll(partitionsI2End);
    		}    
    		
    	}
    	return partitionsBeg2End;
    }
    
    private boolean isPalindrome(String s){
    	if(s == null) {
    		return false;
    	}
    	if(s.length() == 0){
    		return true;
    	}
    	
    	int beg = 0;
    	int end = s.length() - 1;
    	
    	while(beg < end){
    		if(s.charAt(beg) != s.charAt(end)){
    			return false;
    		}
    		--end;
    		++beg;
    	}
    	return true;
    }
}

