package com.example.distributedlock.zookeeperlock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuwh
 * @date 2018/12/7 16:52
 * @desc
 */
public class CuratorUtil {

    private static String address = "10.200.152.34:2181";

    public static void main(String []args){
        //1.重试策略：初始时间为1s 重试3次
        RetryPolicy policy = new ExponentialBackoffRetry(1000,3);

        //2.通过工厂创建链接
        CuratorFramework client = CuratorFrameworkFactory.newClient(address,policy);

        client.start();

        // 4.分布式锁
        final InterProcessMutex  mutex = new InterProcessMutex(client,"curator/lock");

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<5;i++){
            executorService.submit(()->{
               boolean flag = false;
               try{
                   flag = mutex.acquire(5, TimeUnit.SECONDS);

                   if (flag) {
                       System.out.println("线程" + Thread.currentThread().getId() + "获取锁成功");
                   } else {
                       System.out.println("线程" + Thread.currentThread().getId() + "获取锁失败");
                   }
                   //模拟业务逻辑，延时4秒
                   Thread.sleep(4000);
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   if(flag){
                       try{
                           mutex.release();
                       }catch (Exception e){
                           e.printStackTrace();
                       }
                   }
               }
            });
        }
    }
}
