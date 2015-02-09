package org.swan.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Valid Parentheses
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.  The brackets must close in the correct order, 
 * "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * @author Shenghua Wan
 *
 */

public class ValidParentheses {
	public static void main(String[] args){
		Solution sol = new Solution();
		System.out.println(sol.isValid(""));
		System.out.println(sol.isValid("()"));
		System.out.println(sol.isValid("()()"));
		System.out.println(sol.isValid("(())"));
		System.out.println(sol.isValid("())"));
		System.out.println(sol.isValid(")[]("));
		System.out.println(sol.isValid("[[]]"));
	}
}

class Solution{
    public boolean isValid(String s) {
        Map<Character, Character> symbolPairs = new HashMap<Character, Character>(); 
        symbolPairs.put(Character.valueOf(')'), Character.valueOf('('));
        symbolPairs.put(']', '['); //autoboxing, same effect with above
        symbolPairs.put('}', '{');
        
 
        
        Stack<Map<Character, Integer>> stk = new Stack<Map<Character, Integer>>();
        
        for(int i = 0; i < s.length(); i++){
        	char c = s.charAt(i);
        	switch(c) {
        	case '(': case '{': case '[':
        		if(stk.empty()){
        			Map<Character, Integer> record = new HashMap<Character, Integer>(); //used as a pair for ( and the count
        			record.put(c, 1);
        			stk.push(record);
        		}else{
        			if(stk.peek().containsKey(c)){
        				Map<Character, Integer> record = stk.pop();
        				int count = record.get(c);
        				record.put(c, count + 1);
        				stk.push(record);
        			}else{
            			Map<Character, Integer> record = new HashMap<Character, Integer>(); //used as a pair for ( and the count
            			record.put(c, 1);
            			stk.push(record);       				
        			}
        		}
        		break;
        	case ')': case '}': case ']':
        		if(stk.empty()){
        			return false;
        		}else{ //check top whether it is a match
        			if(stk.peek().containsKey(symbolPairs.get(c))) {
        				Map<Character, Integer> record = stk.pop();
        				int count = record.get(symbolPairs.get(c));
       
        				if(count != 1) { //not last one
        	 				record.put(symbolPairs.get(c), count - 1);
        					stk.push(record);
        				}
        			}else{
        				return false; 
        			}
        		}
        		break;
        	default:
        		return false;
        	}
        }
        if(!stk.empty()){
        	return false; 
        }
        return true;
    }
}

/**
 * Simplified Version
 * The string only contains '(' and ')'. 
 * Determine if the parentheses are valid.
 * e.g. "((", ")(" are not valid.
 */

class SolutionForSimplifiedVersion{
	public boolean isValid(String s){
		if (s == null) {
			return true; //by definition
		}
		
		int nLeftParentheses = 0;
		
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '('){
				nLeftParentheses++;
			}else if(c == ')'){
				nLeftParentheses--;
				if (nLeftParentheses < 0){ // cases like ")(", "))(("
					return false;
				}
			}
		}
		
		if (nLeftParentheses == 0){
			return true;
		}
		return false;
	}
}