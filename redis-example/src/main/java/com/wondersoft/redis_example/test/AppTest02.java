package com.wondersoft.redis_example.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.wondersoft.redis_example.redis.RedisService;
import com.wondersoft.redis_example.redis.RedisServiceImpl;
import com.wondersoft.redis_example.thread.ConsumeTask;
import com.wondersoft.redis_example.thread.SendTask;
import com.wondersoft.redis_example.util.ExecutorsUtil;

public class AppTest02 {

private static RedisService redisService = new RedisServiceImpl();
	
	
	public static void main(String[] args) {
		File file = new File("E:\\company\\wondersoft\\工作\\工作\\工作簿\\03\\21\\1.txt");
		String content = "";
		try {
			content = FileUtils.readFileToString(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		long start = System.currentTimeMillis();
        for(int i = 0 ;i < 10; i++){
        	ExecutorsUtil.execTask(new SendTask("zzhtest",redisService,content));
        }
        
        ExecutorsUtil.getExec().execute(new ConsumeTask("zzhtest", redisService));
        
        ExecutorsUtil.getExec().shutdown();
        long end = System.currentTimeMillis();
        while(true){
        	try {
        		if(ExecutorsUtil.getExec().isTerminated()){
	           		 System.out.println("结束了！"); 
	           		 end = System.currentTimeMillis();
	           		 System.out.println("cost time is : "+(end - start)+" mills");
	   	             break; 
        		}
        		Thread.currentThread().sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
	}
}
