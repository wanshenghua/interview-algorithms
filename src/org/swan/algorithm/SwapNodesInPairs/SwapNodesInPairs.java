package org.swan.algorithm.SwapNodesInPairs;

public class SwapNodesInPairs {
	public static void main(String[] args){
		ListNode n0 = new ListNode(1);
		ListNode n1 = new ListNode(2);
		n0.next = n1;
		Solution sol = new Solution();
		sol.swapPairs(n0);
	}
}


/**
 * Definition for singly-linked list. */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode n0 = new ListNode(0);
        n0.next = head;
        ListNode p1 = n0;
        ListNode p2 = n0.next;
        
        while(p2 !=  null){
        	ListNode old_p1 = p1;        	
        	p1 = p1.next;
        	p2 = p2.next;
        	if(p2 == null){
        		break;
        	}
        	//swap pair
        	ListNode p2_next = p2.next;
        	p2.next = p1;
        	p1.next = p2_next;
        	old_p1.next = p2;
        	
        	//swap p1,p2
        	ListNode tmp = p1;
        	p1 = p2;
        	p2 = tmp;
        	
        	p1 = p1.next;
        	p2 = p2.next;
        }
        return n0.next;
    }	
}