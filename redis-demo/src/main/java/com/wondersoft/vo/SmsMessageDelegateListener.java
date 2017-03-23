package com.wondersoft.vo;

import java.io.Serializable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("smsMessageDelegateListener")
public class SmsMessageDelegateListener {

	public SmsMessageDelegateListener(){
		
	}

    //监听Redis消息
    public void handleMessage(Serializable message){
    	System.out.println("handleMessage");
        if(message instanceof SmsMessageVo){
        	SmsMessageVo messageVo = (SmsMessageVo) message;
        	
        	System.out.println(messageVo.getContent());
        }
    }
}