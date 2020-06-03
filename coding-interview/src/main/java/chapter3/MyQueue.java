package chapter3;

import java.util.Stack;

/**
 * @author zhuwh
 * @date 2019/2/22 13:45
 * @desc
 */
public class MyQueue<T> {

    Stack<T> stackNew,stackOld;

    public MyQueue(){
        stackNew = new Stack<T>();
        stackOld = new Stack<T>();
    }

    public int size(){
        return stackNew.size()+stackOld.size();
    }


    public void add(T t){
        stackNew.push(t);
    }

    private void shiftStacks(){
        if(stackOld.isEmpty()){
            while (!stackNew.isEmpty()){
                stackOld.push(stackNew.pop());
            }
        }
    }

    public T peek(){
        shiftStacks();
        return  stackOld.peek();
    }

    public T remove(){
        shiftStacks();
        return  stackOld.pop();
    }
}
