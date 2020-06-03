package chapter2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zhuwh
 * @date 2019/2/25 14:57
 * @desc
 */
public class LinkListDemo {


    public boolean delete(LinkListNode node){
       if(node==null||node.next==null){
           return false;
       }
       LinkListNode next = node.next;
        node.data = next.data;
        node.next = next.next;

        return true;

    }

    public void deleteDups(LinkListNode node){
        HashSet<Integer> set = new HashSet<Integer>();
        LinkListNode  n = null;

        while (node!=null){
            if(set.contains(node.data)){
                n.next = node.next;
            }else{
                set.add(n.data);
                n = node;
            }
            node = node.next;
        }
    }

    LinkListNode findBeginning(LinkListNode node){
        LinkListNode slow = node;
        LinkListNode fast = node;


        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast==slow) break;
        }

        if(fast==null||fast.next==null) return null;

        slow = node;

        while (slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;

    }

    LinkListNode partition(LinkListNode node,int x){
        LinkListNode head = node;
        LinkListNode tail = node;
        while (node!=null){
            LinkListNode next = node.next;
            if(node.data<x){
                node.next =head;
                head = node;
            }else{
                tail.next = tail;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        return  head;
    }


    boolean isPalindrome(LinkListNode node){
        LinkListNode fast = node;
        LinkListNode slow = node;

        Stack<Integer> stack = new Stack<Integer>();
        while(node!=null){
            stack.add(node.data);
            slow = slow.next;
            fast = fast.next;
        }
        if(fast!=null){
            slow = slow.next;
        }

        while (slow!=null){
            int top = stack.pop().intValue();
            if(top!=slow.data){
                return false;
            }

            slow = slow.next;
        }

        return true;
    }
}
