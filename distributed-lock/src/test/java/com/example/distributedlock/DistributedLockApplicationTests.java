package com.example.distributedlock;

import com.example.distributedlock.redislock.Info;
import com.example.distributedlock.redislock.MsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockApplicationTests {

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;


	@Autowired
	private MsService service;
	@Test
	public void contextLoads() {
	}


	@Test
	public void set() {
		redisTemplate.opsForValue().set("MSKey", 10013);
	}

	@Test
	public void get(){
		Object value = redisTemplate.opsForValue().get("MSKey");
		if(value!=null) System.out.println(value.toString());

		Integer rev = Integer.valueOf(value.toString());
		System.out.println("Redis GET: " + rev);
	}

	@Test
	public void delete(){
		boolean value = redisTemplate.delete("123");
		System.out.println("Redis GET: " + value);
	}

	@Test
	public void hash() throws InterruptedException{
		HashOperations hashOperations = redisTemplate.opsForHash();
		Info info1 = new Info(1001,"zwh");
		Info info2 = new Info(1002,"zwh");

		if (hashOperations.getOperations().hasKey("info_1001")) {
			//delete
			hashOperations.delete("info_1001", "1001");
			hashOperations.delete("info_1002", "1002");
			Thread.sleep(3000);
		}

		//put
		hashOperations.put("info_1001", "1001", info1);
		hashOperations.put("info_1002", "1002", info2);
		//get
		Info info = (Info) hashOperations.get("info_1001", "1001");

		System.out.println();
		System.out.println(info);
	}

	@Test
	public void seckill() {
		System.out.println("开始");
		//创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(() -> service.seckill(redisTemplate, "MSKEY"));
	}
}
