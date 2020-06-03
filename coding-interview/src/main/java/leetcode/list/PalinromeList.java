package leetcode.list;

public class PalinromeList {

    public static boolean isPalindrome(ListNode head){

        if(head == null ||head.next==null){
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        //通过快慢节点找出中间节点
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //将slow之后链表反转
        while (slow!=null){
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }


        //3.前后链表进行比较，注意若为奇数链表，多1一个节点，然而并不影响判断回文
        while(head!=null&&pre!=null){
            if(head.val!=pre.val) {
                return false;
            }
            head=head.next;
            pre=pre.next;
        }
        return true;


    }

}
