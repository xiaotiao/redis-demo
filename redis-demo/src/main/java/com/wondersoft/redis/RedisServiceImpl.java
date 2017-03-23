package com.wondersoft.redis;


import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("all")
@Service("redisService")
public class RedisServiceImpl implements RedisService {
	private static final String REDIS_HOST="localhost";
	private static final int PORT=6379;
	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
		
	private static JedisPool jedisPool = null;
	private static int TIMEOUT = 10000;
	// 访问密码
	private static String AUTH = "123";
	public RedisServiceImpl(){
		if(null == jedisPool){
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(MAX_IDLE);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, REDIS_HOST, PORT, TIMEOUT, AUTH);
			
		}
		
	}
	public String get(String key) {	
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	public void set(String key, String value)  {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key,value);
		jedis.close();
		
		
	}
	
	public void update(String key, String value)  {
		Jedis jedis = jedisPool.getResource();
		if(jedis.exists(key)){
//			Long time = jedis.ttl(key);
			
			String cartStr=get(key);
			JSONObject old=JSONObject.parseObject(cartStr);
			JSONArray oldArray=(JSONArray) old.get("cartItems");
			
			JSONObject newStr=JSONObject.parseObject(value);
			JSONArray jsonArray=(JSONArray) newStr.get("cartItems");
			
			oldArray.addAll(jsonArray);
			old.put("cartItems", oldArray);
			
			String result = jedis.set(key, value);
			
			jedis.close();
		}else{
			String result = jedis.set(key, value);
		}
		
		
	}

	public void delete(String key){
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		
		
	}

	public boolean exist(String key) {
		Jedis jedis = jedisPool.getResource();
		boolean result = jedis.exists(key);
		return result;
	}

	public boolean cleanRedis() {
		Jedis jedis=jedisPool.getResource();
		
		String result=jedis.flushAll();
		if("OK".equals(result)){
			 return true;
		}
		
		return false;
	}
	
	
	public Long lPush(String key,String... args){
		Jedis jedis = jedisPool.getResource();
		
		Long len = jedis.lpush(key,args);
		jedis.close();
		
		return len;
	}
	
	public static void main(String[] args) {
		RedisService redisService=new RedisServiceImpl();
		redisService.set("today", "fribday");
	}
	public String rPop(String key) {
		Jedis jedis = jedisPool.getResource();
		String popEle = jedis.rpop(key);
		jedis.close();
		
		return popEle;
	}

}
