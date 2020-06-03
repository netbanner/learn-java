package com.example.logback.controller;

import com.example.logback.config.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author zhuwh
 * @date 2019/4/17 14:50
 * @desc
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {


    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Log("ddd")
    @RequestMapping
    public String index(@RequestParam String content){
        LocalDateTime localDateTime = LocalDateTime.now();

        LOGGER.trace("请求参数：content:{}", content);
        LOGGER.debug("请求参数：content:{}", content);
        LOGGER.info("请求参数：content:{}", content);
        LOGGER.warn("请求参数：content:{}", content);
        LOGGER.error("请求参数：content:{}", content);

        return localDateTime + ",content:" + content;
    }
}
