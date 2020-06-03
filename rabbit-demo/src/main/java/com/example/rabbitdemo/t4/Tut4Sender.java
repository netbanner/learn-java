package com.example.rabbitdemo.t4;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zhuwh
 * @date 2018/12/12 17:42
 * @desc
 */
public class Tut4Sender {


    @Autowired
    private AmqpTemplate template;

    @Autowired
    private DirectExchange directExchange;

    private int index;

    private int count;

    private final String[] keys = {"orange","black","green"};

    @Scheduled(fixedDelay = 1000,initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello to");

        if(++index==3){
            index = 0;
        }
        String key = keys[this.index];
        builder.append(key).append(' ').append(Integer.toString(++count));
        String message = builder.toString();

        template.convertAndSend(directExchange.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");

    }
}
