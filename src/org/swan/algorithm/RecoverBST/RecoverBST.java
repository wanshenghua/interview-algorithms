package org.swan.algorithm.RecoverBST;

public class RecoverBST {

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
	TreeNode first;
	TreeNode second;
	TreeNode prev;
	//double pointer scan, like scanning an array
    public void recoverTree(TreeNode root) {
        if(root == null){
        	return ;
        }
        inOrder(root);
        //swap first, second's value
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    
    private void inOrder(TreeNode node){
    	if(node == null){
    		return ;
    	}
    	
    	inOrder(node.left);

		if(prev == null){
			prev = node;  			
		}else{
    		//in-order traversal of BST is increasing
    		if(prev.val > node.val){//(prev, node) violate BST condition
    			if(first == null){
    				first = prev;
    			}
    			second = node;
    		}
    		prev = node;
    	}
    	inOrder(node.right);
    }
}