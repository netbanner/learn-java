package com.example.distributedlock.redislock;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhuwh
 * @date 2018/12/7 16:03
 * @desc
 */
public class MsThread extends Thread {

    private MsService service;

    private String key;

    private RedisTemplate<String,Object> redisTemplate;

    public MsThread(MsService service,String key,RedisTemplate<String,Object> redisTemplate){
        this.service = service;
        this.key = key;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run() {
       service.seckill(redisTemplate,key);
    }
}
