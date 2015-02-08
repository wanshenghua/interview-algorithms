package org.swan.algorithm.WordBreak;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	public static void main(String[] args){
		Solution sol = new Solution();
		HashSet<String> s = new HashSet<>();
		s.add("aaa");
		s.add("aaaa");
		boolean x = sol.wordBreak("aaaaaaa", s);
	}
}

//exponential 
class SolutionTopDown {
    int maxDictWordLength = 0;
    public boolean wordBreak(String s, Set<String> dict) {
        for(String w : dict){
            maxDictWordLength = (maxDictWordLength > w.length()) ? maxDictWordLength : w.length();
        }
        return recursiveHelper(s, 0, s.length(), dict);
    }
    
    private boolean recursiveHelper(String s, int beg, int end, Set<String> dict){
    	if(beg >= end){
    		return true;
    	}
    	boolean flag = false;
        for(int i = beg + 1; i <= end; i++){
        	if(i - beg <= maxDictWordLength){
        		if(dict.contains(s.substring(beg, i))){
        			flag = (flag || recursiveHelper(s, i, end, dict));
        		}
        	}            
        }
        return flag;
    }
}

//analyze problem top-down, solve problem bottom-top
class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
    	if(s.length() == 0){
    		return true;
    	}
    	boolean[] flag = new boolean[s.length()];

    	
    	for(int i = 1; i <= s.length(); i++){
    		if(dict.contains(s.substring(0, i))){
    			flag[i - 1] = true;
    			continue;
    		}
    		for(int j = 1; j < i; j++){
    			//String prefix = s.substring(0, j);
    			String suffix = s.substring(j, i);
    			if(dict.contains(suffix) && flag[j - 1]){
    				flag[i - 1] = true;
    				break;
    			}
    		}
    	}
    	return flag[s.length() - 1];
    }   
}
