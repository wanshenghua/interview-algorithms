package org.swan.algorithm.WordBreak;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreakII {

}


class SolutionII {
	LinkedList<String> res = new LinkedList<>();
    
    int maxDictWordLength = 0;
    public List<String> wordBreak(String s, Set<String> dict) {
    	if(s.length() == 0){
    		return res;
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
    	if(!flag[s.length() - 1]) {
    		return res;
    	}
    	
        for(String w : dict){
            maxDictWordLength = (maxDictWordLength > w.length()) ? maxDictWordLength : w.length();
        }
        String t = "";
        recursiveHelper(s, 0, s.length(), dict, t);
        return res;
    }
    
    private void recursiveHelper(String s, int beg, int end, Set<String> dict, String t){
    	if(beg >= end){
    		res.add(t);
    	}
        for(int i = beg + 1; i <= end; i++){
        	if(i - beg <= maxDictWordLength){
        		String sub = s.substring(beg, i);
        		if(dict.contains(sub)){
        			if(t.length() == 0){
        				recursiveHelper(s, i, end, dict, sub);
        			}else{
        				recursiveHelper(s, i, end, dict, t + " " + sub);
        			}
        		}
        	}            
        }
    }
}