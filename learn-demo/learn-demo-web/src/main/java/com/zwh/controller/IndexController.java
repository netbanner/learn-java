package com.zwh.controller;

import com.learn.demo.DemoService;
import com.zwh.common.base.BaseController;
import com.zwh.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/jsp",method = RequestMethod.GET)
    public String jsp(){

        return jsp("/index");
    }

    @RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
    public String thymeleaf(Model model){
        model.addAttribute("host",demoService.sayHello("http://www.derbysoft.com"));
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setAge(11);
        user.setName("zhangsan");
        users.add(user);
        user = new User();
        user.setId(2L);
        user.setAge(22);
        user.setName("lisi");
        users.add(user);
        model.addAttribute("users", users);

        return "/index.html";
    }
}
