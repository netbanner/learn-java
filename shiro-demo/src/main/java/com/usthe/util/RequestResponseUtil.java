package com.usthe.util;

import com.alibaba.fastjson.JSON;
import com.usthe.support.XssSqlHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RequestResponseUtil {

    private static final String STR_BODY = "body";



    public static String getHeader(ServletRequest request, String key) {
        return RequestResponseUtil.getRequest(request).getHeader(key);
    }

    public static Map<String,String> getRequestParameters(ServletRequest request) {
        Map<String,String> dataMap = new HashMap<>(16);
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = (String)enums.nextElement();
            String paraValue = RequestResponseUtil.getRequest(request).getParameter(paraName);
            if(null!=paraValue && !"".equals(paraValue)) {
                dataMap.put(paraName,paraValue);
            }
        }
        return dataMap;
    }


    public static Map<String,String> getRequestHeaders(ServletRequest request){
        Map<String,String> headMap = new HashMap<>(16);

        Enumeration enums =  RequestResponseUtil.getRequest(request).getHeaderNames();
        while (enums.hasMoreElements()){
            String name = (String) enums.nextElement();
            String value = RequestResponseUtil.getRequest(request).getHeader(name);
            if(!StringUtils.isEmpty(value)){
                headMap.put(name,value);
            }
        }
        return headMap;
    }

    public static Map<String,String> getRequestBodyMap(ServletRequest request){
        Map<String,String> dataMap = new HashMap<>(16);
        // 判断是否已经将 inputStream 流中的 body 数据读出放入 attribute
        if(request.getAttribute(STR_BODY)!=null){
            // 已经读出则返回attribute中的body
            return (Map<String,String>)request.getAttribute(STR_BODY);
        }else{
            try {
                Map<String, Object> maps = JSON.parseObject(request.getInputStream(),Map.class);
                maps.forEach((key, value) -> dataMap.put(key, String.valueOf(value)));
                request.setAttribute(STR_BODY,dataMap);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return dataMap;
        }
    }


    public static String getParameter(ServletRequest request, String key) {
        return RequestResponseUtil.getRequest(request).getParameter(key);
    }



    public static HttpServletRequest getRequest(ServletRequest request) {
        return new XssSqlHttpServletRequestWrapper((HttpServletRequest)request);
    }


    public static void responseWrite(String outStr, ServletResponse response){

        response.setContentType("application/json:charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            printWriter.write(outStr);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
