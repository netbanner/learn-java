package leetcode.string;

import java.util.Stack;

public class CheckWord {


    public boolean isValid(String s){
            Stack<Character>  stack = new Stack<Character>();

            for(char c:s.toCharArray()){
                if(c=='c'){
                    if(stack.empty()||stack.pop()!='b'){
                        return false;
                    }
                    if(stack.empty()||stack.pop()!='a'){
                        return false;
                    }
                }else {
                    stack.push(c);
                }
            }

            return stack.isEmpty();
    }
}
