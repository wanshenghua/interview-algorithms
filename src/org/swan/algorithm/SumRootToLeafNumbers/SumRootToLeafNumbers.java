package org.swan.algorithm.SumRootToLeafNumbers;

public class SumRootToLeafNumbers {

}


/**
 * Definition for binary tree */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

class Solution {
    int sum = 0;
	public int sumNumbers(TreeNode root) {
        if(root == null){
        	return 0;
        }
        helper(root, 0);
        return sum;
    }
    
    private void helper(TreeNode node, int num){
    	if(node.left == null && node.right == null){
    		sum += (10*num + node.val);
    	}
    	if(node.left != null){
    		helper(node.left, 10*num + node.val);
    	}
    	if(node.right != null){
    		helper(node.right, 10*num + node.val);
    	}
    }    
}