package org.swan.algorithm.PathSum;

/**
 * Given a binary tree and a sum, determine if the 
 * tree has a root-to-leaf path such that adding up 
 * all the values along the path equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \      \
 *       7    2      1
 * return true, as there exist a root-to-leaf 
 * path 5->4->11->2 which sum is 22.
 * @author SHENGHUA
 *
 */
public class PathSum {
	public static void main(String[] args){
		Solution sol = new Solution();
		TreeNode node = new TreeNode(1);
		node.left = null;
		node.right = null;
		TreeNode node2 = new TreeNode(2);
		node.left = node2;
		node2.left = null;
		node2.right = null;
		System.out.println(sol.hasPathSum(node, 1));
	}
}

/**
 * Definition for binary tree
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
    	return helper(root, sum);
    }
    
    private boolean helper(TreeNode node, int sum){
    	if(node == null) {
    		return false;
    	}
    	if(node.left == null && node.right == null){
    		if(sum - node.val == 0){
    			return true;
    		}
    		return false;
    	}

        return helper(node.left, sum - node.val) || 
        		helper(node.right, sum - node.val);
    }
}
