package com.zwh.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {


    @RequestMapping("/")
    public String hello(){
        return "hello world";
    }
}
