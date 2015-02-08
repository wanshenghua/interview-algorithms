package org.swan.algorithm.UniqueBST;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class UniqueBSTII {

}

/**
 * Definition for binary tree */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; left = null; right = null; }
  }


class SolutionUniqueBSTII {
    public List<TreeNode> generateTrees(int n) {
    	return generateTrees(1, n);
    }
    
    private List<TreeNode> generateTrees(int beg, int end){
    	List<TreeNode> bst = new LinkedList<>();
    	if(beg > end){
    		bst.add(null);
    		return bst;
    	}
    	for(int i = beg; i <= end; i++){
    		List<TreeNode> lefts = generateTrees(beg, i - 1);
    		List<TreeNode> rights = generateTrees(i + 1, end);
    		for(TreeNode left: lefts){
    			for(TreeNode right: rights){
    				TreeNode root = new TreeNode(i);
    				root.left = left;
    				root.right = right;
    				bst.add(root);
    			}
    		}
    		
    	}
    	return bst;
    }
}