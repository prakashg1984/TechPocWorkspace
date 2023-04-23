package com.example.thread.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainThread {

	public static void main(String[] args) {
		try {
			ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
			for (int i = 0; i < 8; i++) {
				SampleThread threadObj = new SampleThread();
				executorService.scheduleWithFixedDelay(threadObj, 0, 5, TimeUnit.SECONDS);
	        }
			
			executorService.shutdown();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
