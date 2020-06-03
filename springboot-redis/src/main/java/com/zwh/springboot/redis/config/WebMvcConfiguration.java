package com.zwh.springboot.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author zhuwh
 * @date 2018/7/27 16:28
 * @desc
 */
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       // converters.add(new FastJsonHttpMessageConverter4());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("添加拦截器");
   //     registry.addInterceptor(new PerformanceInterceptor());
    }
}
