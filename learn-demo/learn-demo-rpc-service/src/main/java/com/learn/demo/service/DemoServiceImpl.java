package com.learn.demo.service;

import com.learn.demo.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "hello world"+name;
    }
}
