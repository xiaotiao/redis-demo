package com.wondersoft.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package:com.wondersoft.dlp.base.util
 * @Description:
 * @author:quwu
 * @Version：v1.0
 * @ChangeHistoryList：version author date description v1.0 quwu 2014-8-25
 *                            下午20:44:04
 */

public class ExecutorsUtil {
	private static int THREAD_COUNT=30;
	private static ExecutorService exec;
	private static ExecutorService execPro;
	
	private static Object lock=new Object();
	private static Object lock2=new Object();
	
	public static ExecutorService getExec(){
		synchronized(lock){
			if(exec==null){
				exec = Executors.newFixedThreadPool(THREAD_COUNT);
			}
		}
		return exec;
	}
	
	public static ExecutorService getExec2(){
        synchronized(lock2){
            if(execPro==null){
                execPro = Executors.newFixedThreadPool(THREAD_COUNT);
            }
        }
        return execPro;
    }
	
	public static void execTask(Runnable task){
		ExecutorService executor = getExec();
		executor.execute(task);
	}
	
	public static void execTaskPro(Runnable task){
        ExecutorService executor = getExec2();
        executor.execute(task);
    }
	
	public static void execTasks(Runnable task, int size){		
		for(int i = 0 ; i < size;i++){
			execTask(task);
		}
	}
	
	
}
