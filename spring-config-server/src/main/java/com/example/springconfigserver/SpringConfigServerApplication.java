package com.example.springconfigserver;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@PropertySource("classpath:application.yml")
public class SpringConfigServerApplication {

	private static String postUrl;

	@Value("${refresh.url}")
	private String refreshUrl;

	//解决Spring无法注入静态域
	@PostConstruct
	public void init(){
		postUrl = refreshUrl;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringConfigServerApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringConfigServerApplication.class, args);
		try {
			System.out.println(postUrl);
			HttpResponse httpResponse = Request.Post(postUrl).execute().returnResponse();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				LOGGER.info("配置文件更新推送成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
