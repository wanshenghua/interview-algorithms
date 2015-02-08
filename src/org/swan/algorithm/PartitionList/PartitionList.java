package org.swan.algorithm.PartitionList;

/**
 * Given a linked list and a value x, partition 
 * it such that all nodes less than x come before 
 * nodes greater than or equal to x. 
 * You should preserve the original relative order 
 * of the nodes in each of the two partitions.
 * For example, 
 * Given 1->4->3->2->5->2 and x = 3, 
 * return 1->2->2->4->3->5.
 * @author SHENGHUA
 *
 */
public class PartitionList {
	public static void main(String[] args){
		ListNode v1 = new ListNode(1);
		ListNode v4 = new ListNode(4);
		ListNode v3 = new ListNode(3);
		ListNode v21 = new ListNode(2);
		ListNode v5 = new ListNode(5);
		ListNode v22 = new ListNode(2);		
		v1.next = v4;
		v4.next = v3;
		v3.next = v21;
		v21.next = v5;
		v5.next = v22;
		
		Solution sol = new Solution();
		ListNode head = sol.partition(v1, 3);
		
		while(head != null){
			System.out.println(head.val);
			head = head.next;
		}
	}
}

/**
 * Definition for singly-linked list.
 */ 
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

class Solution {	
	public ListNode partition(ListNode head, int x) {
		if(head == null){
			return head;
		}

		ListNode pseudoHead0 = new ListNode(0);
		pseudoHead0.next = head;
		ListNode pseudoHead1 = new ListNode(0);
		ListNode tmp = pseudoHead1;
		
		ListNode p_prev = pseudoHead0;
		ListNode p = head;
		while(p!=null){
			if(p.val >= x){
				pseudoHead1.next = p;
				p_prev.next = p.next;
				p = p.next;
				pseudoHead1 = pseudoHead1.next;
			}else{
				p_prev = p_prev.next;
				p = p.next;
			}
		}
		//close 2nd list
		pseudoHead1.next = null;
		pseudoHead1 = tmp;
		p_prev.next = pseudoHead1.next;
		return pseudoHead0.next;
	}
}

