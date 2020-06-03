package chapter3;

import java.util.EmptyStackException;

/**
 * @author zhuwh
 * @date 2019/2/22 10:44
 * @desc
 */
public class ThreeStacks {

    int stackSize = 100;
    int[] buffer = new int[stackSize*3];
    int[] stackPointer = {-1,-1,-1};

    void push(int stackNum,int value) throws Exception{

        if(stackPointer[stackNum]+1>=stackSize){
            throw new Exception();
        }
        stackPointer[stackNum]++;
        buffer[absTopOfStack(stackNum)] = value;

    }

    int pop(int stackNum) throws Exception{
        if(isEmpty(stackNum)) throw  new EmptyStackException();

        int value = buffer[absTopOfStack(stackNum)];
        buffer[absTopOfStack(stackNum)] = 0;
        stackPointer[stackNum]--;
        return value;
    }

    int peek(int stackNum) throws Exception{
        if(isEmpty(stackNum)) throw new EmptyStackException();
        int index = absTopOfStack(stackNum);
        return buffer[index];
    }

    boolean isEmpty(int stackNum){
        return stackPointer[stackNum]==-1;
    }

    int absTopOfStack(int stackNum){
        return stackNum*stackSize+stackPointer[stackNum];
    }
}
