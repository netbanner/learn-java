package com.example.kafka;

import com.example.kafka.bean.Message;
import com.example.kafka.producer.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootKafkaDemoApplicationTests {

	@Autowired
	private KafkaSender<Message> kafkaSender;

	@Test
	public void contextLoads() {
	}


	@Test
	public void send() throws Exception{
		for(int i=0;i<5;i++){
			Message message = new Message();
			message.setId(System.currentTimeMillis());
			message.setMsg(UUID.randomUUID().toString());
			message.setTime(new Date());

			kafkaSender.send(message);

		}
	}

}
