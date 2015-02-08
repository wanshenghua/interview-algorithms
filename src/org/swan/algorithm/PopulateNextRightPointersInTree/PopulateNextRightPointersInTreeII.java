package org.swan.algorithm.PopulateNextRightPointersInTree;

public class PopulateNextRightPointersInTreeII {

}


class SolutionII{
    public void connect(TreeLinkNode root) {
        if(root == null){
        	return;
        }
        
        TreeLinkNode p = root.next; //sameLayerLeftmostChild 
        while(p != null){
        	if(p.left != null){
        		p = p.left;
        		break;
        	}
        	if(p.right != null){
        		p = p.right;
        		break;
        	}
        	p = p.next;
        }
        if(root.right != null){
        	root.right.next = p;
        }
        if(root.left != null){
        	root.left.next = (root.right == null) ? p : root.right;
        }
        
        connect(root.right);
        connect(root.left);
    }
}