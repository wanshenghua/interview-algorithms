package org.swan.algorithm.RestoreIPAddress;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddress {
	public static void main(String[] args){
		Solution sol = new Solution();
		List<String> res = sol.restoreIpAddresses("25525511135");
		for(String s : res){
			System.out.println(s);
		}
	}
}

class Solution {
	private List<String> res = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
    	if(s == null || s.length() == 0){
    		return res;
    	}
    	partitionIP(s, 0, 4, "");
        return res;
    }
    
    void partitionIP(String s, int beg, int segments, String ans){
    	if(beg >= s.length()){
    		return;
    	}

    	if(segments == 1){
    		String prefix = s.substring(beg);
    		if(isValidIPSegment(prefix)){
    			res.add(ans + '.' + prefix);
    		}
    		return;
    	}

    	for(int i = 1; i <= 3; i++){//each segments contains max 3 digits 
    		if(beg + i < s.length()){
	    		String prefix = s.substring(beg, beg + i);//substring(b, e) property [b...e-1]
	    		if(isValidIPSegment(prefix)){
	    			if(segments == 4)
	    				partitionIP(s, beg + i, segments - 1, prefix);
	    			else{
	    				partitionIP(s, beg + i, segments - 1, ans + '.' + prefix);
	    			}
	    			
	    		}
    		}
    	}
    }
    
    boolean isValidIPSegment(String s){
    	if(s.length() == 0 || s.length() >= 4){
    		return false;
    	}
    	if(s.length() == 1){
    		return true;
    	}
    	int num = Integer.parseInt(s);
    	if(s.length() == 2){
    		return num <= 99 && num >= 10;
    	}
    	//s.length() == 3
    	return num <= 255 && num >= 100;
    }
}