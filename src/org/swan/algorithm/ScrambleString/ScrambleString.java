package org.swan.algorithm.ScrambleString;

import java.util.HashMap;

public class ScrambleString {
	public static void main(String[] args){
		SolutionScrambleStringDP sol = new SolutionScrambleStringDP();
		System.out.println(sol.isScramble("abb", "abb"));
	}
}

class Solution {
    public boolean isScramble(String s1, String s2) {
    	if(s1 == null && s2 == null){
    		return true;
    	}
    	if(s1.equals("") && s2.equals("")){
    		return true;
    	}
        return recursiveHelper(s1, s2);
    }
    
    private boolean recursiveHelper(String s1, String s2){
    	System.out.println(s1 + ", " + s2);    	
    	if(s1.length() != s2.length()){
    		return false;
    	}

    	if(s1.length() == 1){
    		return s1.equals(s2);
    	}
    	if(s1.length() == 2){
    		return s1.equals(s2) || 
    				(s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0));
    	}
    	
    	for(int i = 1; i < s1.length(); i++){
    		String s11 = s1.substring(0, i);
    		String s12 = s1.substring(i, s1.length());
	    	String s21 = s2.substring(0, i);
	    	String s22 = s2.substring(i, s2.length());
	    	if(shareSameAlphabet(s1, s2) && recursiveHelper(s11, s21) && recursiveHelper(s12, s22)){
	    		return true;
	    	}
	    	s21 = s2.substring(0, s2.length() - i);
	    	s22 = s2.substring(s2.length() - i, s2.length());
	    	if(shareSameAlphabet(s1, s2) && recursiveHelper(s11, s22) && recursiveHelper(s12, s21)){
	    			return true;
	    		}
      	}
    	return false;
    }
    
    private boolean shareSameAlphabet(String s1, String s2){
    	HashMap<Character, Integer> char2numForS1 = new HashMap<>();  	
    	for(char c : s1.toCharArray()){
    		if(char2numForS1.containsKey(c)){
    			char2numForS1.put(c, char2numForS1.get(c) + 1);
    		}else{
    			char2numForS1.put(c, 1);
    		}
    	}
    	
    	for(char c :s2.toCharArray()){
    		if(char2numForS1.containsKey(c)){
    			char2numForS1.put(c, char2numForS1.get(c) - 1);
    		}else{
    			return false;
    		}
    	}
    	
    	for( Integer count: char2numForS1.values()){
    		if(count!=0){
    			return false;
    		}
    	}
    	
    	return true;
    }
}

class SolutionScrambleStringDP {
    public boolean isScramble(String s1, String s2) {
    	int n = s1.length();
    	if(n != s2.length()){
    		return false;
    	}
    	boolean[][][] A = new boolean[n][n][n+1];
    	//trivial case
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			A[i][j][1] = (s1.charAt(i) == s2.charAt(j));
    		}
    	}
    	//bottom-top solution
    	for(int p = 2; p <= n; p++){
    		for(int i = 0; i <= n - p; i++){
    			for(int j = 0; j <= n - p; j++){
    				for(int k = 1; k < p; k++){
    					A[i][j][p] |= ((A[i][j][k] && A[i+k][j+k][p-k]) ||
    							(A[i][j+p-k][k] && A[i+k][j][p-k]));
    				}
    			}
    		}
    	}
    	return A[0][0][n];
    }
}