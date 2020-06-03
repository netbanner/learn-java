package com.example.rabbitdemo.t6;

/**
 * @author zhuwh
 * @date 2018/12/13 11:20
 * @desc
 */
public class Tut6Server {

    public int process(int in){
        System.out.println(" [x] Received request for " + in);
        int result = fib(in);
        System.out.println(" [.] Returned " + result);
        return result;
    }
    /**
     * 斐波那契数
     *
     * @param i
     * @return
     */
    private int fib(int i) {
        return (i == 0 || i == 1) ? i : (fib(i - 2) + fib(i - 1));
    }

}
