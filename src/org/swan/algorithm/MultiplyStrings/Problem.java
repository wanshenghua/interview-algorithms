package org.swan.algorithm.MultiplyStrings;

import java.util.Arrays;

/**
 * Given two numbers represented as strings, 
 * return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
 * @author SHENGHUA
 *
 */
public class Problem {
	public static void main(String[] args){
		Solution sol = new Solution();
		String res = sol.multiply("123", "456");
		System.out.println(res);
	}
}

/**
 * Analysis:
 * When multiplying two number strings, we can first obtain 
 * a intermediate results by multiplying digits, e.g.
 * a2|a1|a0 x b1|b0 =
 *       b0*a2|b0*a1|b0*a0
 * b1*b2|b1*a1|b1*a0
 * Then we can obtain another intermediate result by adding 
 * the corresponding digits.
 * Finally we calculate the carry from lower digit to higher one.
 * 
 * Solution:
 * 1. Reverse the input strings, pay attention to heading 0's
 * 2. product of i-digit and j-digit will go to i+j digit
 * @author SHENGHUA
 *
 */

class Solution {
    public String multiply(String num1, String num2) {
    	String ns1 = new StringBuilder(num1).reverse().toString();
    	String ns2 = new StringBuilder(num2).reverse().toString();
    	
    	int[] prod = new int[ns1.length() + ns2.length()];
    	for(int i = 0; i < ns1.length(); i++) {
    		int n1 = ns1.charAt(i) - '0';
    		for(int j = 0; j < ns2.length(); j++) {
    			int n2 = ns2.charAt(j) - '0';
    			prod[i + j] += n1 * n2;
    		}
    	}
    	
    	StringBuilder res = new StringBuilder();
    	int carry = 0;
    	for(int i = 0; i < prod.length; i++){
    		int val = prod[i] + carry; 
    		carry = val / 10;
    		int residual = val % 10;
    		res.insert(0, residual);
    	}
    	//trim starting 0s, could remove all digits
    	while(res.length() > 0 && res.charAt(0) == '0') {
    		res.deleteCharAt(0);
    	}
        if(res.length() == 0){
        	return "0";
        }
        return res.toString();
    }
}

