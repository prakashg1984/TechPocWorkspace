package com.pg.samplespringboot;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {

	@Async("threadPoolExecutor")
	public void executeBean() {
		
	System.out.println("Execute method with configured executor - "+Thread.currentThread().getName());
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
