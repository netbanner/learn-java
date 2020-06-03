package chapter3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author zhuwh
 * @date 2019/2/22 11:24
 * @desc
 */
public class SetOfStacks {

    ArrayList<Stack> stacks = new ArrayList<Stack>();



    public void push(int v){
        Stack last = getLastStack();

        if(last!=null&&!last.isEmpty()){
            last.push(v);
        }else{
            Stack stack = new Stack();
            stack.push(v);
            stacks.add(stack);
        }
    }

    public void pop() {
        Stack last = getLastStack();
        Object v = last.pop();
        if(last.isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }

    }

    public Stack getLastStack(){
        if(stacks.size()==0) return  null;

        return stacks.get(stacks.size()-1);
    }

}
