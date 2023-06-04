package com.example.runnable;

public class SampleRunnable implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Thread " + Thread.currentThread().getId() + " is started");
			Thread.sleep(2000);
			System.out.println("Thread " + Thread.currentThread().getId() + " is ending");
		} catch (Exception e) {
			System.out.println("Exception is caught");
		}
		
	}

}
