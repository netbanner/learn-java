package com.example.rabbitdemo.t6;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zhuwh
 * @date 2018/12/13 11:20
 * @desc
 */
public class Tut6Client {

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private DirectExchange exchange;


    private int start =0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        System.out.println(" [x] Requesting fib(" + start + ")");
        Integer response = (Integer) template.convertSendAndReceive(exchange.getName(), "rpc", start++);
        System.out.println(" [.] Got '" + response + "'");

    }


}
