package com.example.rabbitdemo.t4;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author zhuwh
 * @date 2018/12/12 17:41
 * @desc
 */
public class Tut4Config {

    @Bean
    public DirectExchange directExchange(){

        return new DirectExchange("tut3.direct");
    }

    private static class ReceiverConfig{

        public Queue autoQueue1(){
            return new AnonymousQueue();
        }

        public Queue autoQueue2(){
            return  new AnonymousQueue();
        }

        public Binding binding1a(DirectExchange exchange,Queue queue){

            return BindingBuilder.bind(queue).to(exchange).with("orange");
        }


        /**
         * 使用路由 key 绑定到 black
         *
         * @param directExchange
         * @param autoQueue1
         * @return
         */
        @Bean
        public Binding bindin1b(DirectExchange directExchange, Queue autoQueue1) {
            return BindingBuilder.bind(autoQueue1).to(directExchange).with("black");
        }

        @Bean
        public Binding bindin2a(DirectExchange directExchange, Queue autoQueue2) {
            return BindingBuilder.bind(autoQueue2).to(directExchange).with("green");
        }

        @Bean
        public Binding binding2b(DirectExchange directExchange, Queue autoQueue2) {
            return BindingBuilder.bind(autoQueue2).to(directExchange).with("black");
        }

        @Bean
        public Tut4Receiver receiver() {
            return new Tut4Receiver();
        }
    }
    @Profile("sender04")
    @Bean
    public Tut4Sender sender() {
        return new Tut4Sender();
    }
}
