package org.swan.algorithm.Permutations;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into 
 * the lexicographically next greater permutation of numbers. 
 * If such arrangement is not possible, it must rearrange it 
 * as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * @author SHENGHUA
 *
 */
public class NextPermutation {

}

/**
 * From wiki
 * The following algorithm generates the next permutation 
 * lexicographically after a given permutation. It changes 
 * the given permutation in-place. 
 * 
 * 1. Find the largest index k such that a[k] < a[k + 1]. If no 
 * such index exists, the permutation is the last permutation.
 *  
 * 2. Find the largest index l greater than k such that a[k] < a[l]. 
 * 
 * 3. Swap the value of a[k] with that of a[l].
 * 
 * Reverse the sequence from a[k + 1] up to and including the final 
 * element a[n].
 * 
 * 自己的思路
 * 1. 如果元素是从左到右降序排列，那么这个就是最后一个排列。所以，我们要寻找从右到左第一个
 * 下降的元素，称为a[k],此时说明a[k]和a[k+1...n]可以重新排列找到下一个排列方式。
 * a[k]那个位置需要变大一点才满足“下一个”的要求，但不能太大否则就是下“一”个了。
 * 注意 如果1能找到a[k]，那么a[k+1...n]是降序排列的；我们知道升序排列是最小的。
 * 3. 接着我们重新从右到左找到第一个比a[k]大的，记为a[i]；因为a[k+1...n]是降序的，
 * 所以a[i+1...n]都比a[k]小，不是合适的候选。最小的候选就是a[i]。
 * 4.交换a[i]和a[k]；此时a[k+1...n]仍然是降序，那么最小的排列就是a[k+1...n]的逆转，
 * 因为逆转后结果就是升序了。
 * 
 * related problems Permutations, Permutation II
 * @author SHENGHUA
 *
 */

class NextPermutationSolution {
    public void nextPermutation(int[] num) {
        //find a[k]
    	int k = num.length - 2;
    	for(; k >= 0; k--) {
    		if(num[k] < num[k+1]){
    			break;
    		}
    	}
    	if(k == -1) { //no such k=>last permutation
    		Arrays.sort(num);
    		return ;
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
    }
    
    private void reverse(int[] a, int i, int j) {
    	int beg = i;
    	int end = j;
    	while(beg < end) {
    		int tmp =  a[beg];
    		a[beg] = a[end];
    		a[end] = tmp;
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