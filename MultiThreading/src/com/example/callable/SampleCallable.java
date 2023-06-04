package com.example.callable;

import java.util.concurrent.Callable;

public class SampleCallable implements Callable {

	@Override
	public String call() throws Exception {
		System.out.println("Thread " + Thread.currentThread().getName() + " is started");
		Thread.sleep(2000);
		System.out.println("Thread " + Thread.currentThread().getName() + " is ending");
		return Thread.currentThread().getName();
	}

}
