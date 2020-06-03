package com.example.configserverdemo;


import com.example.configserverdemo.model.User;
import com.example.configserverdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class CoreController {
    private static Logger LOGGER = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    private UserService userService;

    @Value("${userurl}")
    private String userurl;

    @GetMapping("userurl")
    public String getUserurl(){
        LOGGER.info("core--userurl");
        return userurl;
    }

    @GetMapping("saveUser")
    public String saveUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        userService.saveUser(user);
        return name;
    }
}
