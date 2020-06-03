package com.zwh.springboot.redis.controller;

import com.zwh.springboot.redis.lock.DistributedLock;
import com.zwh.springboot.redis.service.RedisLockService;
import com.zwh.springboot.redis.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuwh
 * @date 2018/7/27 16:42
 * @desc
 */
@RestController
@RequestMapping("redislock")
public class RedisLockController {

    private final Logger logger = LoggerFactory.getLogger(RedisLockController.class);

    @Autowired
    private RedisLockService redisLockService;

    @Autowired
    private DistributedLock redisLock;

    @RequestMapping("aspect")
    public String lockAspect(){
        for(int i=0; i<10; i++){
            new RedisLockAspectThread().start();
        }
        return "aspect";
    }


    @RequestMapping("lock")
    public String lock(){
        for(int i=0; i<10; i++){
            new RedisLockThread().start();
        }
       return "lock";
    }

    class RedisLockThread extends Thread {

        @Override
        public void run() {
            String key = "lockKey";
            boolean result = redisLock.lock(key);
            logger.info(result ? "get lock success : " + key : "get lock failed : " + key);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("exp", e);
            } finally {
                redisLock.realseLock(key);
                logger.info("release lock : " + key);
            }
        }
    }

    class RedisLockAspectThread extends Thread {

        @Override
        public void run() {
//			String key = "lockKey2";
//			redisLockService.update(key);

            UserVO userVO = new UserVO();
            userVO.setId(10L);
            userVO.setName("fuwei.deng");
            redisLockService.update(userVO);
        }
    }
}
