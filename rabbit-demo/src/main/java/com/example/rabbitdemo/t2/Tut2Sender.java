package com.example.rabbitdemo.t2;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zhuwh
 * @date 2018/12/12 16:55
 * @desc
 */
public class Tut2Sender {

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private Queue queue;

    int dots = 0;
    int count = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder stringBuilder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }
        for (int i = 0; i < dots; i++) {
            stringBuilder.append(".");
        }
        stringBuilder.append(Integer.toString(++count));
        String message = stringBuilder.toString();

        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
