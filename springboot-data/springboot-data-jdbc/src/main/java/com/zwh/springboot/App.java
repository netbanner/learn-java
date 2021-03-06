package com.zwh.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

    @Bean
    public CommandLineRunner exampleQuery(CarRespository repository) {
        return args -> repository.findByMake("dda")
                .forEach(System.err::println);
    }
}
