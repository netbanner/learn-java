package com.zwh.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.rich.InMemoryRichGaugeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{



    @Bean
    @Primary
    public InMemoryRichGaugeRepository inMemoryRichGaugeRepository(){
        return new InMemoryRichGaugeRepository();
    }
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
