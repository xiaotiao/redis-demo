package com.wondersoft.thread;

import com.wondersoft.redis.RedisService;

public class SendTask implements Runnable{
	
	private RedisService redisService;
	private String key;
	private String[] args;
	
	public SendTask(String key,RedisService redisService,String... args){
		this.key = key;
		this.redisService = redisService;
		this.args = args;
	}

	public void run() {
		long start = System.currentTimeMillis();
        for(int i = 0;i< 100000;i++){
        	redisService.lPush(key, args);
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println("cost time= "+(end -start) +" mills");
	}

}
