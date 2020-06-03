package com.example.rabbitdemo.t1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author zhuwh
 * @date 2018/12/12 16:17
 * @desc
 */
public class Tut1Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMsg(){
        String msg = "Hello World!" + new Date();
        amqpTemplate.convertAndSend(queue.getName(), msg);
        System.out.println(" [x] Sent '" + msg + "'");
    }
}
