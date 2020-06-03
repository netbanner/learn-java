package com.example.rabbitdemo.t5;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author zhuwh
 * @date 2018/12/13 11:09
 * @desc
 */

@Profile({"tut5","topics"})
@Configuration
public class Tut5Config {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("tut5.topic");
    }

    private static class ReceiveConfig{

        @Bean
        public Queue autoQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(TopicExchange exchange, Queue autoQueue1) {
            return BindingBuilder.bind(autoQueue1).to(exchange).with("*.orange.*");
        }

        @Bean
        public Binding binding2a(TopicExchange exchange, Queue autoQueue2) {
            return BindingBuilder.bind(autoQueue2).to(exchange).with("*.*.rabbit");
        }

        @Bean
        public Binding binding2b(TopicExchange exchange, Queue autoQueue2) {
            return BindingBuilder.bind(autoQueue2).to(exchange).with("lazy.#");
        }

        @Bean
        public Tut5Receiver receiver() {
            return new Tut5Receiver();
        }
    }

    @Profile("sender05")
    @Bean
    public Tut5Sender sender() {
        return new Tut5Sender();
    }
}
