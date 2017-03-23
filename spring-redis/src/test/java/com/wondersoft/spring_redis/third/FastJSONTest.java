package com.wondersoft.spring_redis.third;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class FastJSONTest {

	@Test
	public void testSerialRate(){
		long start = System.currentTimeMillis();
		for(int i=0;i<100000;i++){
			JSONObject obj = JSONObject.parseObject("");
		}
		long end = System.currentTimeMillis();
		
		System.out.println("cost time is:"+(end - start)+" mills");
	}
	
	@Test
	public void testSerialOrder(){
		
	}
}
