package com.example.logback.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuwh
 * @date 2019/4/17 14:25
 * @desc
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface  Log {

    /**
     * 说明信息
     *
     * @return
     */
    String value() default "";

    /**
     * 是否忽略
     *
     * @return
     */
    boolean ignore() default false;
}
