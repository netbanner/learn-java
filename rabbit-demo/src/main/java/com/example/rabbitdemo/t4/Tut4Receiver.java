package com.example.rabbitdemo.t4;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @author zhuwh
 * @date 2018/12/12 17:41
 * @desc
 */
public class Tut4Receiver {


    @RabbitListener(queues = {"#{autoQueue1.name"})
    public void receive1(String inMsg) throws InterruptedException{
        receiver(inMsg,1);
    }

    @RabbitListener(queues = {"#{autoQueue2.name"})
    public void receive2(String inMsg) throws InterruptedException{
        receiver(inMsg,2);
    }

    private void receiver(String in,int instance) throws InterruptedException{
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + instance + " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + instance + " [x] Done in " +
                watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException{
        for(char ch:in.toCharArray()){
            if(ch=='.'){
                Thread.sleep(1000);
            }
        }
    }
}
