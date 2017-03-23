package com.wondersoft.spring_redis.thread;

import com.wondersoft.spring_redis.dao.StringDao;


public class ConsumeStrTask implements Runnable{
	private StringDao stringDao;
	private String key;
	private static volatile int count=0;
	
	public ConsumeStrTask(String key, StringDao stringDao){
		this.key = key;
		this.stringDao = stringDao;
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		while(true){
			String content = stringDao.getMsg(key);
			if(++count == 1000000){
				long end = System.currentTimeMillis();
				System.out.println("consume time is = "+(end - start)+" mills");
				break;
				
			}
		}
		
	}

}
