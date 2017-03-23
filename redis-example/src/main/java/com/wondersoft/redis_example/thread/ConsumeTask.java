package com.wondersoft.redis_example.thread;

import com.wondersoft.redis_example.redis.RedisService;


public class ConsumeTask implements Runnable{
	private RedisService redisService;
	private String key;
	private static volatile int count=0;
	
	public ConsumeTask(String key,RedisService redisService){
		this.key = key;
		this.redisService = redisService;
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		while(true){
			String popEle = redisService.rPop(key);
			if(++count == 1000000){
				long end = System.currentTimeMillis();
				System.out.println("consume time is = "+(end - start)+" mills");
				
				break;
				
			}
		}
		
	}

}
