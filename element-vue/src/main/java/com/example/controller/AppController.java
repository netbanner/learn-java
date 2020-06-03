package com.example.controller;

import com.example.beans.ResultBean;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    UserService userService;


    public ResultBean<Boolean> logout(HttpSession session){

        session.invalidate();

        return new ResultBean<Boolean>(true);

    }
}
