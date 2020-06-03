package offer;

import leetcode.list.ListNode;

import java.util.ArrayList;
import java.util.Stack;

public class PrintListFromTailToHead {


    public ArrayList<Integer> print(ListNode head){
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        while (head!=null){
           stack.push(head.val);
           head = head.next;
        }

        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

    private static void revert(ListNode node) {

        if (node != null) {
            revert(node.next);
            System.out.println(node.val);
        }
    }
}
