package com.zwh.springboot.redis.service;

import com.zwh.springboot.redis.annotation.LockAction;
import com.zwh.springboot.redis.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zhuwh
 * @date 2018/7/27 16:36
 * @desc
 */
@Service
public class RedisLockService {

    private final Logger logger = LoggerFactory.getLogger(RedisLockService.class);

    @LockAction
    public void update(String key){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            logger.error("exp",e);
        }
    }

    @LockAction("#user.id")
    public void update(UserVO user){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            logger.error("exp",e);
        }
    }
}
