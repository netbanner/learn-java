package com.example.distributedlock.redislock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zhuwh
 * @date 2018/12/4 17:01
 * @desc
 */
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate<String, Object> redisTemplate;
    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
    private String lockKey;

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    private int expireMsecs = 60 * 1000;
    /**
     * 锁等待时间，防止线程饥饿
     */
    private int timeoutMsecs = 10 * 1000;

    private volatile boolean locked = false;

    public RedisLock(RedisTemplate<String,Object> redisTemplate ,String lockKey){
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey+"_lock";
    }

    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int timeoutMsecs) {
        this(redisTemplate, lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        this(redisTemplate, lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    /**
     *
     * @param: [key]
     * @return java.lang.String
     * @author zhuwh
     * @date 2018/12/4 17:15
     */
    public  String get(final String key){
        Object o = null;
        try{
             o = redisTemplate.execute((RedisCallback<Object>) redisConnection->{
                 StringRedisSerializer redisSerializer = new StringRedisSerializer();
                 byte []data = redisConnection.get(redisSerializer.serialize(key));
                 redisConnection.close();
                 if (data == null) {
                     return null;
                 }
                 return redisSerializer.deserialize(data);
             } );
        }catch (Exception e){
            logger.error("get redis error, key : {}", key);
        }

        return o != null ? o.toString() : null;
    }

    /**
     *
     * @param: [key, value]
     * @return java.lang.String
     * @author zhuwh
     * @date 2018/12/4 17:20
     */
    public String set(final String key,String value){
        Object o = null;

        try{
            o = redisTemplate.execute((RedisCallback<Object>) redisConnection->{
                StringRedisSerializer redisSerializer = new StringRedisSerializer();
               redisConnection.set(redisSerializer.serialize(key),redisSerializer.serialize(value));
               redisConnection.close();
               return  redisSerializer;
            });
        }catch (Exception e){
            logger.error("set redis error, key : {},value:{}", key,value);
        }

        return o != null ? o.toString() : null;
    }


    /**
     *
     * @param: [key, value]
     * @return boolean
     * @author zhuwh
     * @date 2018/12/4 17:24 
     */ 
    public boolean setNX(final String key ,final String value){
        Object object = null;

        try{
            object = redisTemplate.execute((RedisCallback<Object>) redisConnection->{
               StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
               Boolean success = redisConnection.setNX(stringRedisSerializer.serialize(key),stringRedisSerializer.serialize(value));
               redisConnection.close();
               return  success;
            });
        }catch (Exception e){
            logger.error("setNX redis error, key : {},value:{}", key,value);
        }

        return object != null ? (Boolean) object : false;
    }

    private String getSet(final String key, final String value) {
        Object object = null;
        try {
            object = redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
                StringRedisSerializer redisSerializer = new StringRedisSerializer();
                byte[] data = redisConnection.getSet(redisSerializer.serialize(key), redisSerializer.serialize(value));
                redisConnection.close();

                return redisSerializer.deserialize(data);
            });
        } catch (Exception e) {
            logger.error("getSet redis error, key : {}", key);
        }
        return object != null ? object.toString() : null;
    }

    public synchronized boolean lock() throws InterruptedException{
        int timeout = timeoutMsecs;
        while (timeout>0){
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            // 锁到期时间
            String expiresStr = String.valueOf(expires);

            if(this.setNX(this.lockKey,expiresStr)){
                locked = true;
                return true;
            }
        }

        return  false;
    }


    /**
     * 释放锁
     */
    public synchronized void unlock() {
        if (locked) {
            redisTemplate.delete(this.lockKey);
            locked = false;
        }
    }
}
