package com.wondersoft.spring_redis.thread;

import com.wondersoft.spring_redis.dao.OrderDao;
import com.wondersoft.spring_redis.vo.Order;


public class ConsumeTask implements Runnable{
	private OrderDao orderDao;
	private String key;
	private static volatile int count=0;
	
	public ConsumeTask(String key, OrderDao orderDao){
		this.key = key;
		this.orderDao = orderDao;
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		while(true){
			Order order = orderDao.getOrderMsg(key);
			if(++count == 1000000){
				long end = System.currentTimeMillis();
				System.out.println("consume time is = "+(end - start)+" mills");
				break;
				
			}
		}
		
	}

}
