package com.example.rabbitdemo.t1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author zhuwh
 * @date 2018/12/12 16:13
 * @desc
 */
@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {

    @Bean
    public Queue queue(){
        return  new Queue("hello-world");
    }


    @Profile("receiver01")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender01")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }

}
