package org.swan.algorithm.SimplifyPath;

import java.util.Stack;

public class SimplifyPath {
	public static void main(String[] args){
		Solution sol = new Solution();
		System.out.println(sol.simplifyPath("/home/of/foo/../../bar/../../is/./here/."));

	}
}

class Solution {
    public String simplifyPath(String path) {
    	String[] elem = path.split("/");
    	Stack<String> stack = new Stack<>();
    	
    	for(int i = 0; i < elem.length; i++){
    		String e = elem[i];
    		if(!e.equals("") && !e.equals(".")){
    			stack.push(e);
    		}
    	}
    	
    	String res = "";
		int count = 0;
    	while(!stack.empty()){
    		String s = stack.peek();
    		if(s.equals("")){
    			stack.pop();
    		}else if(s.equals("..")) {
  
    			while(!stack.empty() && stack.peek().equals("..")){
    				count++;
    				stack.pop();
    			}
    			while(!stack.empty() && count > 0 && !stack.peek().equals("..")){
    				stack.pop();
    				count--;
    			}
    		}else{
    			res = ("/" + s) + res;
    			stack.pop();
    		}
    	}
    	if(res.length() == 0){
    		res = "/";
    	}
    	return res;
    }
}