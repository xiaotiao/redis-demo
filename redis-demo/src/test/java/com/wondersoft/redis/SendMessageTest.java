package com.wondersoft.redis;

import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.wondersoft.vo.SendMessage;
import com.wondersoft.vo.SmsMessageVo;

public class SendMessageTest {
	ApplicationContext context = null;
	
	@Before
	public void setup(){
		String logxml = "log4j.xml";
		DOMConfigurator.configure(logxml);
		context = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		
	}

	@Test
	public void testSendMessage(){
		
		 SmsMessageVo smsMessageVo = new SmsMessageVo();
		 smsMessageVo.setContent(new Date().toString());
		 SendMessage sendMessage = new SendMessage();
		 sendMessage.setRedisTemplate(context.getBean(RedisTemplate.class));
		 sendMessage.sendMessage("sms_queue_web_online", smsMessageVo);
		 
		 while(true){
			 try {
				Thread.currentThread().sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}
	
	@Test
	public void testJedisConnectionFactory(){
//		JedisConnectionFactory factory = new JedisConnectionFactory();
//		factory.afterPropertiesSet();
//		factory.setHostName("192.168.54.4");
//		factory.setPort(6379);
//		factory.setPassword("123");
//		factory.setUsePool(true);
//		
//		JedisConnection connection = factory.getConnection();
//		
//		String result = new String(connection.get("name".getBytes()));
//		
//		System.out.println("result="+result);
		
		JedisConnectionFactory factory = context.getBean(JedisConnectionFactory.class);
		JedisConnection connection = factory.getConnection();
		String result = new String(connection.get("name".getBytes()));
		System.out.println("result="+result);
		
	}
	
	@Test
	public void testStr(){
		String str = "{\"time\":\"Mon Mar 20 20:15:12 CST 2017\",\"type\":\"log\",\"event\":\"sun qq login\"}";
		System.out.println(str.getBytes().length);
	}
}
