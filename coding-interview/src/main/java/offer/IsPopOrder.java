package offer;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，
 * 请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 */
public class IsPopOrder {

    public boolean isPopOrder(int []pushSequence,int []popSequence){
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();

        for(int pushIndex =0,popIndex =0;pushIndex<n;pushIndex++){
            stack.push(pushSequence[pushIndex]);
            while (popIndex<n&&!stack.isEmpty()&&stack.peek()==popSequence[popIndex]){
                stack.pop();
                popIndex++;
            }
        }


        return stack.isEmpty();
    }

    public static void main(String []args){
        int []a = {1,2,3,4,5};
        int []b = {4,3,5,1,2};
        IsPopOrder popOrder = new IsPopOrder();

        System.out.println(  popOrder.isPopOrder(a,b));
    }
}
