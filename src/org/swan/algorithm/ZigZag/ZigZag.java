package org.swan.algorithm.ZigZag;

public class ZigZag {
	public static void main(String[] args){
		Solution sol = new Solution();
		String s = sol.convert("ABC", 2);
		System.out.println(s);
	}
}


class Solution {
	public String convert(String s, int nRows) {
	    if(s == null || s.length() <= 1 || nRows == 1){
	        return s;
	    }
	    
		String res = "";
		int step  = 2 * nRows - 2;
		for(int i = 0; i < nRows; i++){	
			for(int base = i;; base += step){
				if(base >= s.length()){
					break;
				}
				res += s.charAt(base);
				int offset = step - 2 * i;
				if(i < nRows - 1 && i > 0) {
					if(base + offset >= s.length()){
						break;
					}
					res += s.charAt(base + offset);					
				}
			}		
		}
		return res;
    }
}