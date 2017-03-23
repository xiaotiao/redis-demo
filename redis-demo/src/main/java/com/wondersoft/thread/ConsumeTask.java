package com.wondersoft.thread;

import com.wondersoft.redis.RedisService;

public class ConsumeTask implements Runnable{
	private RedisService redisService;
	private String key;
	
	public ConsumeTask(String key,RedisService redisService){
		this.key = key;
		this.redisService = redisService;
	}
	
	public void run() {
		String popEle = redisService.rPop(key);
		System.out.println(popEle);
	}

}
