package org.swan.algorithm.RotateList;

public class RotateList {
	public static void main(String[] arg){
		ListNode n0 = new ListNode(1);
		ListNode n1 = new ListNode(2);
		n0.next = n1;
		n1.next = null;
		Solution sol = new Solution();
		sol.rotateRight(n0, 1);
	}
}

/**
 * Definition for singly-linked list.
 *  */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if(head == null || head.next == null){
        	return head;
        }
    	int len = 0;
        ListNode p = new ListNode(0);
        ListNode tmp = p;
        p.next = head;
        while(p.next!=null){
        	len++;
        	p = p.next;
        }
        int k = n % len;
        if(k == 0){
        	return head;
        }
        p = tmp;
        int i = 0;
        while(i != len - k){
        	i++;
        	p = p.next;
        }
        ListNode q = p.next;
        tmp = q;
        p.next = null;
        while(q.next != null){
        	q = q.next;
        }
        q.next = head;
        return tmp;
    }
}