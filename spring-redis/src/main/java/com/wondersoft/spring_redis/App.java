package com.wondersoft.spring_redis;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wondersoft.spring_redis.dao.OrderDao;
import com.wondersoft.spring_redis.dao.StringDao;
import com.wondersoft.spring_redis.thread.ConsumeStrTask;
import com.wondersoft.spring_redis.thread.ConsumeTask;
import com.wondersoft.spring_redis.thread.SendStrTask;
import com.wondersoft.spring_redis.thread.SendTask;
import com.wondersoft.spring_redis.util.ExecutorsUtil;
import com.wondersoft.spring_redis.vo.Order;

/**
 * 示例
 * 
 */
public class App {
	
	public static void main(String[] args) {
		String configPath = "redis.xml";
		File file = new File("/opt/ws/app/test.txt");
		String detail = "";
		try {
			detail = FileUtils.readFileToString(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ApplicationContext context = new ClassPathXmlApplicationContext(
				configPath);

//		OrderDao orderDao = context.getBean(OrderDao.class);
//
//		Order order = new Order();
//		order.setId("1");
//		order.setOrderNo("00000001");
//		order.setPrice(80);
//		order.setCreateDate(new Date());
//		order.setDetail(detail);
		StringDao stringDao = context.getBean(StringDao.class);
		long start = System.currentTimeMillis();
        for(int i = 0 ;i < 10; i++){
        	ExecutorsUtil.execTask(new SendStrTask("orderKey",stringDao,detail));
        }
        
        ExecutorsUtil.getExec().execute(new ConsumeStrTask("orderKey", stringDao));
        
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
