package org.swan.algorithm.MinimumWindowSubstring;

import java.util.HashMap;

public class Problem {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String res = sol.minWindow("a", "a");
		System.out.println(res);
	}
}
/** Problem 
 * Given a string S and a string T, find the minimum window in S 
 * which will contain all the characters in T in complexity O(n).
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
 */

/**
 * Analysis:
 * T is charCounts, or more specifically table of character counts to satisfy,
 * e.g. T="abbcccdddd" means we are looking for 1a,2b,3c,4d. 
 * This is a critical condition, or a pattern.
 * 
 * Solution:
 * We can maintain two pointer, beg and end. 
 * end is always moving forward and we keep track of table of character counts.
 * When the condition is satisfied,				
 * if(charCounts.get(c) <= pattern.get(c)){ count++; } //an excellent checking method
 * we can keep moving beg pointer if either of the conditions is met:
 *    (1) [beg] is a character not in the pattern, or
 *    (2) the number of [beg] is required and exceeds the number required by the pattern  
 * In condition (1), simply move beg forward
 * In condition (2), move beg forward and reduce the number of [beg] in record
 * When (1) or (2) could not be met, we check the window size and make
 * a record for minimum window size.
 * 
 * Note that the could be no such window. 
 * @author SHENGHUA
 *
 */
class Solution {
	public String minWindow(String S, String T) {
		String res = "";
		HashMap<Character, Integer> pattern = new HashMap<>();
		HashMap<Character, Integer> charCounts = new HashMap<>();
		//fill pattern, table of character counts
		for(int i = 0; i < T.length(); i++) {
			if(pattern.containsKey(T.charAt(i))) {
				pattern.put(T.charAt(i), pattern.get(T.charAt(i)) + 1);
			}else {
				pattern.put(T.charAt(i), 1);
				charCounts.put(T.charAt(i), 0);
			}
		}
		//flag for no such window
		int minWinBeg = -1;
		int minWinEnd = S.length();
		
		int count = 0;
		for(int beg = 0, end = 0; end < S.length(); end++) {
			char c = S.charAt(end);
			if(pattern.containsKey(c)) {
				//update table of character counts
				charCounts.put(c, charCounts.get(c) + 1);
				//approach required pattern
				if(charCounts.get(c) <= pattern.get(c)){
					count++; 
				}
				//condition to move beg
				if(count == T.length()) {
					while(!pattern.containsKey(S.charAt(beg)) ||
							charCounts.get(S.charAt(beg)) > pattern.get(S.charAt(beg))) {
						if(pattern.containsKey(S.charAt(beg)) && 
								charCounts.get(S.charAt(beg)) > pattern.get(S.charAt(beg))) {
							charCounts.put(S.charAt(beg), charCounts.get(S.charAt(beg)) - 1);
						}
						beg++;
					}
					//update smaller window
					if(end - beg < minWinEnd - minWinBeg) {
						minWinEnd = end;
						minWinBeg = beg;
					}
				}
			}
		}
		if (minWinBeg == -1) {
			res = "";
		}else {
			res =  S.substring(minWinBeg, minWinEnd + 1);
		}
		return res;
	}
}
/*
class Solution {
	public String minWindow(String S, String T) {
		HashMap<Character, Integer> dictT = getCharStatistics(T);
		HashMap<Character, Integer> dictS = getCharStatistics(S);
		
		return minWindow(S, 0, S.length() - 1, dictT, dictS);
		
	}
	
	public String minWindow(String S, int beg, int end, HashMap<Character, Integer> dictT, HashMap<Character, Integer> dictS) {
		if(beg > end){
			return "";
		}
		String lSub = S;
		String rSub = S; 
		
		Character bL = S.charAt(beg);
		Character eL = S.charAt(end);
		int curFbL = dictS.get(bL);
		int curFeL = dictS.get(eL);
		
		if(!dictT.containsKey(bL) || curFbL > 2) {
			beg -= 1;
			dictS.put(bL, curFbL - 1);
		}
		
		if(!dictT.containsKey(eL) || curFeL > 2) {
			end -= 1;
			dictS.put(eL, curFeL - 1);
		}
		

		return null;
	}
	
	public HashMap<Character, Integer> getCharStatistics(String S) {
		HashMap<Character, Integer> charFrequency = new HashMap<>();
		for(int i = 0; i < S.length(); i++) {
			Character c = Character.valueOf(S.charAt(i));
			if(charFrequency.containsKey(c)) {
				charFrequency.put(c, charFrequency.get(c) + 1);
			}else {
				charFrequency.put(c, 1);
			}
		}
		return charFrequency;
	}
}*/