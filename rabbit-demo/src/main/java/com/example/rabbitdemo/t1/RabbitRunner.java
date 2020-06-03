package com.example.rabbitdemo.t1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

import javax.security.auth.login.Configuration;

/**
 * @author zhuwh
 * @date 2018/12/12 16:31
 * @desc
 */
public class RabbitRunner implements CommandLineRunner {

    @Value("${tutorial.client,duration:0}")
    private int duration;

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ready ... running for " + duration + "ms");
        Thread.sleep(duration);
        configurableApplicationContext.close();
    }
}
