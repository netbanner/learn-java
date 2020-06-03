package chapter3;

import java.util.Stack;

/**
 * @author zhuwh
 * @date 2019/2/22 10:27
 * @desc
 */
public class SortStack {


    public static Stack<Integer> sort(Stack<Integer> stack){
        Stack<Integer> r = new Stack<Integer>();
        while (!stack.empty()){
            int tmp = stack.pop();
            while (!r.empty()&&r.peek()>tmp){
                stack.push(r.pop());
            }
            r.push(tmp);
        }

        return  r;
    }
}
