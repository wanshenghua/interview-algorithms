package org.swan.algorithm.SymmetricTree;

public class SymmetricTree {

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
    public boolean isSymmetric(TreeNode root) {
    	if(root == null){
    		return true;
    	}
    	return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode lt, TreeNode rt){
    	if(lt == null && rt == null){
    		return true;
    	}
    	if((lt != null && rt == null) ||
    			(lt == null && rt != null) ||
    				(lt.val != rt.val)){
    		return false;
    	}
    	return helper(lt.left, rt.right) &&
    			helper(lt.right, rt.left);
    }
}