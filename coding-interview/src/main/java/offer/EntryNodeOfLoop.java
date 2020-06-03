package offer;

import leetcode.list.ListNode;

/**
 * 链表中环的入口结点
 */
public class EntryNodeOfLoop {

    public ListNode solution(ListNode head){
     if(head==null){
         return null;
     }
     ListNode slow = head,fast = head;

     do{
         fast = fast.next.next;
         slow = slow.next;
     }while (fast!=slow);
     fast = head;
     while (slow!=fast){
         slow = slow.next;
         fast = fast.next;
     }
     return  slow;
    }
}
