package com.example.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2/")
public class MenuV2Controller {

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    @GetMapping(value ="test",produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String test(){
        Map<String, String> pinfo = new HashMap<>();
        Context context = new Context();
        context.setVariable("pinfo", pinfo);
        pinfo.put("lastname", "Jordan");
        pinfo.put("firstname", "Michael");
        pinfo.put("country", "USA");

        String content = springTemplateEngine.process("person-details",context);
        return content;

    }

}
