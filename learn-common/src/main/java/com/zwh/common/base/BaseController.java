package com.zwh.common.base;

import com.zwh.common.util.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  abstract  class BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    public static String jsp(String path){

        return path.concat(".jsp");
    }

    public static String thymeleaf(String path){
        String folder = PropertiesFileUtil.getInstance().get("app.name");
        return "/".concat(folder).concat(path).concat(".html");
    }
}
