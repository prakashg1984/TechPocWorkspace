package com.example.listeners;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

public class CustomItemWriterListener implements ItemWriteListener<Object> {

	@Override
	public void beforeWrite(List<? extends Object> items) {
		System.out.println("beforeWrite : "+items);
		
	}

	@Override
	public void afterWrite(List<? extends Object> items) {
		System.out.println("afterWrite : "+items);
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Object> items) {
		System.out.println("onWriteError : "+items);
		
	}

}
