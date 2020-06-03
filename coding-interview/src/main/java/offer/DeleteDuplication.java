package offer;
/**
 * 删除链表中重复的结点
 */

import leetcode.list.ListNode;

public class DeleteDuplication {

    public ListNode delete(ListNode head){

        if(head==null||head.next==null){
            return head;
        }
        ListNode next = head.next;
        if(head.val==next.val){
            while (next!=null&&head.val==next.val){
                next = next.next;
            }
            return delete(next);
        }else{
            head.next = delete(head.next);
            return head;
        }
    }

}
