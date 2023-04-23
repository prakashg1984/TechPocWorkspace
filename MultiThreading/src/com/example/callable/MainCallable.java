package com.example.callable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainCallable {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
        Set<Callable<String>> callables = new HashSet<Callable<String>>();  

		for (int i = 0; i < 8; i++) {
			SampleCallable callableObj = new SampleCallable();
			callables.add(callableObj);
        }
		
        try {
			java.util.List<Future<String>> futures = executorService.invokeAll(callables);
			
			for(Future<String> future : futures){  
	            System.out.println("future.get thread name = " + future.get());  
	        }  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        executorService.shutdown();  

	}

}
