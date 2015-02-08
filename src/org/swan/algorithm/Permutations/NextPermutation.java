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
 * �Լ���˼·
 * 1. ���Ԫ���Ǵ����ҽ������У���ô����������һ�����С����ԣ�����ҪѰ�Ҵ��ҵ����һ��
 * �½���Ԫ�أ���Ϊa[k],��ʱ˵��a[k]��a[k+1...n]�������������ҵ���һ�����з�ʽ��
 * a[k]�Ǹ�λ����Ҫ���һ������㡰��һ������Ҫ�󣬵�����̫���������¡�һ�����ˡ�
 * ע�� ���1���ҵ�a[k]����ôa[k+1...n]�ǽ������еģ�����֪��������������С�ġ�
 * 3. �����������´��ҵ����ҵ���һ����a[k]��ģ���Ϊa[i]����Ϊa[k+1...n]�ǽ���ģ�
 * ����a[i+1...n]����a[k]С�����Ǻ��ʵĺ�ѡ����С�ĺ�ѡ����a[i]��
 * 4.����a[i]��a[k]����ʱa[k+1...n]��Ȼ�ǽ�����ô��С�����о���a[k+1...n]����ת��
 * ��Ϊ��ת�������������ˡ�
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