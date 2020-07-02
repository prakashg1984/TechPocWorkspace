package com.example.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("beforeJob "+jobExecution.toString());
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("afterJob "+jobExecution.toString());
	}

	

}
