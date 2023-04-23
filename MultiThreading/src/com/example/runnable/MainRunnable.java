package com.example.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunnable {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 8; i++) {
			SampleRunnable runnableObj = new SampleRunnable();
			executorService.execute(runnableObj);
        }
		
		executorService.shutdown();
		
		
		
		while (!executorService.isTerminated()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Not completed");
		}
		  
		
		System.out.println("ALL completed");
		
	}

}
