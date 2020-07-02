package com.example.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("beforeStep : "+stepExecution);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("afterStep : "+stepExecution);
		return null;
	}

}
