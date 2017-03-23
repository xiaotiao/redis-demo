package com.wondersoft.spring_redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.wondersoft.spring_redis.vo.Order;

@Repository  
public class StringDao {  
  
    @Autowired  
    private RedisTemplate<String,String> redisTemplate;  
  
   
    public Long sendMsg(String key,String content){
    	
    	return redisTemplate.opsForList().leftPush(key, content);
    }
    
    public String getMsg(String key){
    	
    	return redisTemplate.opsForList().rightPop(key);
    }
}  
