package com.example.springbootreact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuwh
 * @date 2018/11/28 16:33
 * @desc
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String index(){

        return "Hello , dalaoyang ";

    }
}
