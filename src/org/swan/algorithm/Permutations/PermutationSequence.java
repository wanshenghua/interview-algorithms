package org.swan.algorithm.Permutations;


/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, We get 
 * the following sequence (ie, for n = 3): 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * Given n and k, return the kth permutation sequence. 
 * Note: Given n will be between 1 and 9 inclusive.
 * 这题还有一个公式解，不需要遍历 。
 * @author SHENGHUA
 *
 */
public class PermutationSequence {
	public static void main(String[] args){
		PermutationSequenceSolution sol = new PermutationSequenceSolution();
		
		
		for(int i = 0; i < 5*4*3*2; i++){
			System.out.printf("%d: %s\n", i+1, sol.getPermutation(5, i+1));
		}
		
	}
}

/**
 * 一个直接的解法是调用改版的nextPermutation
 * 
 * @author SHENGHUA
 *
 */
class PermutationSequenceSolution {
    public String getPermutation(int n, int k) {
    	StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
        	sb.append((char)(i + 1 + '0'));
        }
        for(int i = 1; i < k; i++){
        	nextPermutation(sb);
        }
        return sb.toString();
    }
    
    public boolean nextPermutation(StringBuilder sb) {
        //find a[k]
    	int k = sb.length() - 2;
    	for(; k >= 0; k--) {
    		if(sb.charAt(k) < sb.charAt(k+1)){
    			break;
    		}
    	}
    	if(k == -1) { //no such k=>last permutation
    		reverse(sb, 0, sb.length() - 1);
    		return false;
    	}
    	//find a[i]
    	int i = sb.length() - 1;
    	for(; i >= 0; i--){
    		if(sb.charAt(k) < sb.charAt(i)) {
    			break;
    		}
    	}
    	//swap
    	swap(sb, i, k);
    	//reverse a[k+1..n]
    	reverse(sb, k + 1, sb.length() - 1);
    	return true;
    }    
    
    private void reverse(StringBuilder sb, int i, int j) {
    	int beg = i;
    	int end = j;
    	while(beg < end) {
    		swap(sb, beg, end);
    		--end;
    		++beg;
    	}     	
    }
    
    private void swap(StringBuilder sb, int i, int j){
		char tmp =  sb.charAt(i);
		sb.setCharAt(i, sb.charAt(j));
		sb.setCharAt(j, tmp);
    }
}

