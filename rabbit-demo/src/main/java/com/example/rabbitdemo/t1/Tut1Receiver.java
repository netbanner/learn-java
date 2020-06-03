package com.example.rabbitdemo.t1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author zhuwh
 * @date 2018/12/12 16:16
 * @desc
 */
@RabbitListener(queues = "hello-world")
public class Tut1Receiver {

    @RabbitHandler
    public void receive(String in){
        System.out.println(" [x] Received '" + in + "'");
    }
}
