package com.example.listeners;

import org.springframework.batch.core.ItemReadListener;

public class CustomItemReaderListener implements ItemReadListener<Object> {

	@Override
	public void beforeRead() {
		System.out.println("beforeRead");
		
	}

	@Override
	public void afterRead(Object item) {
		System.out.println("afterRead "+item);
		
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("onReadError "+ex);
		
	}

}
