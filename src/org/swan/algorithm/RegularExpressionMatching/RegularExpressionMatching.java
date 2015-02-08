package org.swan.algorithm.RegularExpressionMatching;

public class RegularExpressionMatching {

}

class Solution {
	//match first character and do the rest recursively
	//(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
    public boolean isMatch(String s, String p) {
        if(p.length() == 0){
        	return s.length() == 0;
        }
        if(p.length() == 1){
        	return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        }
        //p is at least of length 2
        if(p.charAt(1) != '*'){
        	if(s.length() < 1){
        		return false;
        	}
        	//match first character and check recursively of the suffix
        	return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') 
        			&& isMatch(s.substring(1), p.substring(1));
        }
        //only match first char
        while(s.length() > 0 &&
        		(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){
        	//* matches 0 of p[0]
        	if(isMatch(s, p.substring(2))){//when * is used
        		return true;
        	}
        	//* matches 1 or more of p[0], greedy matching
        	s = s.substring(1);
        }
        //when * is not used
        //c* => 0 of c, skip p[0]
        return isMatch(s, p.substring(2));
    }
}