package com.wondersoft.spring_redis.thread;


import com.wondersoft.spring_redis.dao.StringDao;

public class SendStrTask implements Runnable{
	
	private StringDao stringDao;
	private String key;
	private String content;
	
	public SendStrTask(String key,StringDao stringDao,String content){
		this.key = key;
		this.stringDao = stringDao;
		this.content = content;
	}

	public void run() {
		long start = System.currentTimeMillis();
        for(int i = 0;i< 100000;i++){
        	stringDao.sendMsg(key, content);
        }
		
        long end = System.currentTimeMillis();
        System.out.println("cost time= "+(end -start) +" mills");
	}

}
