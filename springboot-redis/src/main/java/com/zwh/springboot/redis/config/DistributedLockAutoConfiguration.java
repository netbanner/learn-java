package com.zwh.springboot.redis.config;

import com.zwh.springboot.redis.lock.DistributedLock;
import com.zwh.springboot.redis.lock.RedisDistributedLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhuwh
 * @date 2018/7/27 15:04
 * @desc
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributedLockAutoConfiguration {


    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public DistributedLock distributedLock(RedisTemplate<Object,Object> redisTemplate){

        return  new RedisDistributedLock(redisTemplate);
    }

}
