package com.zwh.springboot.redis.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author zhuwh
 * @date 2018/7/27 14:48
 * @desc
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LockAction {

    @AliasFor("key")
    String value() default "'default'";

    @AliasFor("value")
    String key() default "'default'";


    long keepMills() default 30000;

    LockFailAction action() default LockFailAction.CONTINUE;

    public enum LockFailAction{
        //放弃
        GIVEUP,
        //继续
        CONTINUE;
    }

    //重试的间隔时间
    long sleepMills() default 200;

    //重复次数
    int retryTimes() default 5;
}
