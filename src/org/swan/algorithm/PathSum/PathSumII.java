package org.swan.algorithm.PathSum;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PathSumII {
	public static void main(String[] args){
		SolutionII sol = new SolutionII();
		TreeNode node = new TreeNode(1);
		node.left = null;
		node.right = null;
//		TreeNode node2 = new TreeNode(2);
//		node.left = node2;
//		node2.left = null;
//		node2.right = null;
		List<List<Integer>> res = sol.pathSum(node, 1);

	}
}

class SolutionII {
	List<List<Integer>> res = new LinkedList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList<Integer> path = new LinkedList<>();
        helper(root, sum, path);
        return res;
    }
    
    private void helper(TreeNode node, int sum, LinkedList<Integer> path){
    	if(node == null){
    		return;
    	}
    	if(node.left == null && node.right == null){
    		if(sum - node.val == 0){
    			LinkedList<Integer> found_path = new LinkedList<>(path);
    			found_path.add(node.val);
    			res.add(found_path);
    			return;
    		}
    		return;
    	}
    	path.add(node.val);
    	helper(node.left, sum - node.val, path);
    	helper(node.right, sum - node.val, path);
    	path.removeLast();
    }
}
