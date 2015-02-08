package org.swan.algorithm.Subsets;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Subsets {
	public static void main(String[] arg){
		Solution sol = new Solution();
		int[] x = new int[1];
		List<List<Integer>> res = sol.subsets(x);
		int a = 1;
	}
}

class Solution {
    List<List<Integer>> res = new LinkedList<List<Integer>>();
	public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        LinkedList<Integer> path = new LinkedList<>();
        res.add(path);
        DFSHelper(S, 0, S.length, path);
        return res;
    }
    
    private void DFSHelper(int[] S, int beg, int end, LinkedList<Integer> path){
    	if(beg == end){
    		return;
    	}
    	for(int i = beg; i < end;i++){
    		path.addLast(S[i]);
    		res.add(new LinkedList<Integer>(path));
    		DFSHelper(S, i + 1, end, path);
    		path.removeLast();
    	}
    }
}