package com.example.controller;

import com.example.beans.ResultBean;
import com.example.beans.Roles;
import com.example.beans.User;
import com.example.config.ServerCfg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @PostMapping("/login")
    public ResultBean<User> login(HttpSession session, String username) {
        User user = new User();
        user.setId(1000L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setName("abc");
        user.setNick("我曹");
        user.setRole(Roles.ADMIN);

        return new ResultBean<>(user);
    }

    @Autowired
    ServerCfg cfg;

    @GetMapping(value = "/configTest")
    public ResultBean<ServerCfg> configTest() {
        return new ResultBean<>(cfg);
    }
}
