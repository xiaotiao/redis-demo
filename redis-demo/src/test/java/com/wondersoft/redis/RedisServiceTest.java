package com.wondersoft.redis;

import org.junit.Before;
import org.junit.Test;

import com.wondersoft.thread.ConsumeTask;
import com.wondersoft.thread.SendTask;
import com.wondersoft.util.ExecutorsUtil;

public class RedisServiceTest {

	private static RedisService redisService;
	
	@Before
	public void init(){
		redisService = new RedisServiceImpl();
	}
	
	@Test
	public void testSet(){
		
		for(int i = 0;i< 100;i++){
			ExecutorsUtil.execTask(new Runnable(){

				public void run() {
					for(int i = 0;i< 100000;i++){
						redisService.set("name", "zzh");
					}
				}
				
			});
		}
	
		System.out.println(redisService.get("name"));
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
        for(int i = 0 ;i < 10; i++){
        	ExecutorsUtil.execTask(new SendTask("zzhtest",redisService,"good start"));
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
