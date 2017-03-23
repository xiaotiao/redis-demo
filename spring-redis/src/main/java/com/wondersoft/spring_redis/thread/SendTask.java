package com.wondersoft.spring_redis.thread;


import com.wondersoft.spring_redis.dao.OrderDao;
import com.wondersoft.spring_redis.vo.Order;

public class SendTask implements Runnable{
	
	private OrderDao orderDao;
	private String key;
	private Order order;
	
	public SendTask(String key,OrderDao orderDao,Order order){
		this.key = key;
		this.orderDao = orderDao;
		this.order = order;
	}

	public void run() {
		long start = System.currentTimeMillis();
        for(int i = 0;i< 100000;i++){
        	orderDao.sendOrderMsg(key, order);
        }
		
        long end = System.currentTimeMillis();
        System.out.println("cost time= "+(end -start) +" mills");
	}

}
