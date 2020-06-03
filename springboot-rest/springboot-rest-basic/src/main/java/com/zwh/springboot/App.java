package com.zwh.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            Arrays.asList("Phil", "Josh").forEach(name -> personRepository
                    .save(new Person(name, (name + "@email.com").toLowerCase())));
            personRepository.findAll().forEach(System.out::println);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
