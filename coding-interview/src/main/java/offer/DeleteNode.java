package offer;

import leetcode.list.ListNode;

/**
 * 在 O(1) 时间内删除链表节点
 * 1:该节点不是尾节点
 * 2：该节点是尾节点
 */
public class DeleteNode {

     public ListNode delete(ListNode head,ListNode deleteNode){
         if(head==null||deleteNode==null){
             return null;
         }
         if(deleteNode.next!=null){
             ListNode temp = deleteNode.next;
             deleteNode.val = temp.val;
             deleteNode.next =deleteNode.next.next;
         }else{
             ListNode cur =head;
             while (cur.next!=deleteNode){
                 cur = cur.next;
             }
             cur.next = null;
         }

         return head;
     }
}
