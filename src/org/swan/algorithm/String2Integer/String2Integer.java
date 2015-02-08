package org.swan.algorithm.String2Integer;

public class String2Integer {
	public static void main(String[] args){
		Solution sol = new Solution();
		//System.out.println(sol.atoi("2147483647"));
		//System.out.println(sol.atoi("-2147483648"));
		//System.out.println(sol.atoi("    10522545459"));
		System.out.println(sol.atoi("      -11919730356x"));
	}
}

class Solution {
	static int INT_MAX = Integer.MAX_VALUE;
	static int INT_MIN = Integer.MIN_VALUE;
    public int atoi(String str) {

    	int beg = 0;
    	while(beg < str.length() && (!isDigit(str.charAt(beg))) && isWhitespace(str.charAt(beg))){
    		beg++;
    	}
    	
    	int end = str.length() - 1;
    	while(end >= 0 && !isDigit(str.charAt(end))){
    		end--;
    	}
    	
    	int sign = 1;
    	int val = 0;
    	int state = 0;
    	for(int i = beg; i <= end; i++){
    		char c = str.charAt(i);
    		if(state == 0){
    			if(c == '+' || c == '-'){
    				if(c == '+'){
    					sign = 1;
    				}else{
    					sign = -1;
    				}
    				state = 1;
    				continue;
    			}else if(isDigit(c)){
    				state = 2;
    				val = (c - '0');     				
    				continue;
    			}
    			state = -1;	
    		}else if(state == 1){
    			if(isDigit(c)){
    				state = 2;
    				val = sign * (c - '0');      				
    				continue;
    			}
    			state = -1;
    		}else if(state == 2){
    			if(isDigit(c)){
    				if(sign == 1){
        				if( (val == this.INT_MAX / 10 && (c - '0') > (this.INT_MAX % 10)) || val > this.INT_MAX / 10){
        					return this.INT_MAX;
        				}else{
        					val = val * 10 + (c - '0');   
        				}
    				}else{
    					if((val == this.INT_MIN / 10 && (c - '0') > -(this.INT_MIN % 10)) || val < this.INT_MIN / 10){
    						return this.INT_MIN;
    					}else{
    						val = val * 10 - (c - '0');
    					}
    				}
    	    		continue;
    			}
    			state = -1;
    		}else if(state == -1){
    			break;
    		}
    	}
    	return val;
    }
    
    boolean isDigit(char c){
    	return Character.isDigit(c);
    }
    
    boolean isWhitespace(char c){
    	return Character.isWhitespace(c);
    }

}
