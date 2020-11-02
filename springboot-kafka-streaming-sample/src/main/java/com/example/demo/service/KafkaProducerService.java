package com.example.demo.service;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaProducerService {
	
	Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
	
	@Resource(name = "RouteEventProducerTemplate")
	private KafkaTemplate<String, String> sender;
	
	@Value("${kafka.topic:SAMPLE_DEV_TOPIC2}")
	private String topic;
	
	public void sendMessage(String message) {
		//System.out.println("New Message Posted : "+message);
		logger.info("New Message Posted : "+message);
		ListenableFuture<SendResult<String,String>> future = sender.send(topic,""+message.hashCode() ,message);
		
		try {
			future.get();
		}catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
