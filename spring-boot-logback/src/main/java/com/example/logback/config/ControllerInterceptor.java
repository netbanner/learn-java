package com.example.logback.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zhuwh
 * @date 2019/4/17 15:06
 * @desc
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {

    private Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String requestUUID = MDC.get("requestUUID");
       if(requestUUID==null||"".equals(requestUUID)){
           String uuid= UUID.randomUUID().toString();
           uuid = uuid.replaceAll("-", "").toUpperCase();
           MDC.put("requestUUID", uuid);
           LOGGER.info("ControllerInterceptor preHandle 在请求处理之前生成 logback requestUUID:{}", uuid);
       }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        /* 线程结束后需要清除,否则当前线程会一直占用这个requestId值 */
        LOGGER.info("ControllerInterceptor postHandle 请求处理之后清除 logback MDC requestUUID");
        MDC.remove("requestUUID");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
          /*整个请求线程结束后需要清除,否则当前线程会一直占用这个requestId值 */
        LOGGER.info("ControllerInterceptor afterCompletion 整个请求处理完毕清除 logback MDC requestUUID");
        MDC.clear();
    }
}
