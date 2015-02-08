package org.swan.algorithm.Permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers, return all possible permutations.
 * For example, [1,2,3] have the following permutations: 
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * @author SHENGHUA
 *
 */
public class Permutations {
	public static void main(String[] args){
		int[] num = new int[] {1, 2, 3, 4, 5};
		PermutationSolution sol = new PermutationSolution();
		List<List<Integer>> list = sol.permute(num);
		
		int n = 0;
		for(List<Integer> l : list) {
			System.out.printf("%d: ", ++n);
			for(Integer i : l) {
				System.out.printf("%d ", i);
			}
			System.out.println();
		}
	}
}

/**
 * 一个直接的解法是调用nextPermutation
 * 
 * @author SHENGHUA
 *
 */
class PermutationSolution {
    public List<List<Integer>> permute(int[] num) {
    	List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(num);
        do{
        	List<Integer> perm = new ArrayList<>();
        	for(int i: num){
        		perm.add(i);
        	}
        	res.add(perm);
        } while(nextPermutation(num));
    	            
        return res;
    }
    
    public boolean nextPermutation(int[] num) {
        //find a[k]
    	int k = num.length - 2;
    	for(; k >= 0; k--) {
    		if(num[k] < num[k+1]){
    			break;
    		}
    	}
    	if(k == -1) { //no such k=>last permutation
    		reverse(num, 0, num.length - 1);
    		return false;
    	}
    	//find a[i]
    	int i = num.length - 1;
    	for(; i >= 0; i--){
    		if(num[k] < num[i]) {
    			break;
    		}
    	}
    	//swap
    	swap(num, i, k);
    	//reverse a[k+1..n]
    	reverse(num, k + 1, num.length - 1);
    	return true;
    }
    
    private void reverse(int[] a, int i, int j) {
    	int beg = i;
    	int end = j;
    	while(beg < end) {
	    	swap(a, beg, end);
    		--end;
    		++beg;
    	}     	
    }
    
    private void swap(int a[], int i, int j){
    	int tmp = a[i];
    	a[i] = a[j];
    	a[j] = tmp;   	
    }
}
