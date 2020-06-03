package com.zwh.springboot.redis.lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author zhuwh
 * @date 2018/7/26 17:36
 * @desc
 */
public class RedisDistributedLock extends AbstractDistributedLock {

    private final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

    private RedisTemplate<Object, Object> redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    public RedisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
       boolean result = setRedis(key,expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
       while ((!result)&&retryTimes-->0){
           try {
               logger.debug("lock failed, retrying..." + retryTimes);
               Thread.sleep(sleepMillis);
           } catch (InterruptedException e) {
               return false;
           }
           result = setRedis(key, expire);
       }

       return result;
    }

    @Override
    public boolean realseLock(String key) {
        try {
            final List<String> keys = new ArrayList<>();
            keys.add(key);
            final List<String> args = new ArrayList<>();
            args.add(lockFlag.get());

            Long result = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Object nativeConnection = redisConnection.getNativeConnection();
                    // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                    // 集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }

                    // 单机模式
                    else if (nativeConnection instanceof Jedis) {
                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    return 0L;
                }
            });

            return result != null && result > 0;
        }catch (Exception e){
            logger.error("release lock error",e);
        }finally {
            // 清除掉ThreadLocal中的数据，避免内存溢出
            lockFlag.remove();
        }
        return false;
    }



    private boolean setRedis(final String key, final long expire){
        try {
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                    String uuid = UUID.randomUUID().toString();
                    lockFlag.set(uuid);
                    return commands.set(key, uuid, "NX", "PX", expire);
                }
            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            logger.error("set redis occured an exception", e);
        }
        return false;
    }
}
