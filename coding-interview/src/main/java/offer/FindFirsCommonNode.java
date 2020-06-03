package offer;

import leetcode.list.ListNode;

/**
 * 两个链表的第一个公共结点
 */
public class FindFirsCommonNode {

    public ListNode find(ListNode node1, ListNode node2) {
        ListNode l1 = node1, l2 = node2;
        while (l1 != l2) {
            l1 = (l1 == null) ? node2 : l1.next;
            l2 = (l2 == null) ? node1 : l1.next;
        }
        return l1;
    }
}
