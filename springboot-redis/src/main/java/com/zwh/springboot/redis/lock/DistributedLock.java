package com.zwh.springboot.redis.lock;

/**
 * @author zhuwh
 * @date 2018/7/26 17:19
 * @desc
 */
public interface DistributedLock {

    public static final long TIMEOUT_MILLIS = 30000;

    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    public static final long SLEEP_MILLIS = 500;

    public boolean lock(String key);

    public boolean lock(String key ,int retryTimes);

    public boolean lock(String key,int retryTimes,long sleepMillis);

    public boolean lock(String key ,long expire);

    public boolean lock(String key,long expire,int retryTimes);

    public boolean lock(String key,long expire,int retryTimes,long sleepMillis);

    public boolean realseLock(String key);




}
