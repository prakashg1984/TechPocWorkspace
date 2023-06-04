package com.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 8; i++) {
			SampleThread threadObj = new SampleThread();
			//threadObj.start();
			executorService.execute(threadObj);
        }
		
		executorService.shutdown();
	}
}
