package org.swan.algorithm.SortList;

public class SortList {
	public static void main(String[] args){
		ListNode n0 = new ListNode(2);
		ListNode n1 = new ListNode(1);
		n0.next = n1;
		n1.next = null;
		Solution sol = new Solution();
		sol.sortList(n0);
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
	    public ListNode sortList(ListNode head) {
	        return mergeSort(head);
	    }
	    
	    private ListNode mergeSort(ListNode head){
	    	if(head == null || head.next == null){
	    		return head;
	    	}
	    	ListNode mid = split(head);
	    	ListNode tmp = mid;
	    	mid = mid.next;
	    	tmp.next = null;
	    	ListNode l0 = mergeSort(head);
	    	ListNode l1 = mergeSort(mid);
	    	return merge(l0, l1);
	    }
	    
	    private ListNode split(ListNode head){
	    	//find middle node of a list
	    	ListNode fakeHead = new ListNode(0);
	    	fakeHead.next = head;
	    	ListNode p0 = fakeHead;
	    	ListNode p1 = fakeHead;
	    	
	    	while(p1.next != null){
	    		p1 = p1.next;
	    		p0 = p0.next;
	    		if(p1.next == null){
	    			break;
	    		}
	    		p1 = p1.next;	    		
	    	}
	    	return p0;
	    }
	    
	    private ListNode merge(ListNode l0, ListNode l1){
	    	ListNode p = new ListNode(0);
	    	ListNode tmp = p;
	    	while(p!=null){
	    		if(l0!=null && l1!=null){
	    			if(l0.val < l1.val){
	    				p.next = l0;
	    				l0 = l0.next;
	    			}else{
	    				p.next = l1;
	    				l1 = l1.next;
	    			}
	    			p = p.next;
	    		}else if(l0!=null && l1==null){
	    			p.next = l0;
	    			l0 = l0.next;
	    			p = p.next;
	    		}else if(l0==null && l1!=null){
	    			p.next = l1;
	    			l1 = l1.next;
	    			p = p.next;
	    		}else{
	    			p = p.next;
	    		}
	    	}
	    	return tmp.next;
	    }
	}