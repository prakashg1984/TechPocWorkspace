package com.demo.kafka.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.kafka.model.User;

import jakarta.annotation.Resource;

@Service
public class MyKafkaService {
	
	@Resource(name = "RouteEventProducerTemplate")
	private KafkaTemplate<String, User> sender;
	
	@Value("${kafka.topic:SAMPLE_DEMO_TOPIC}")
	private String topic;
	
	public void publishToKafka(User user) {
		CompletableFuture<SendResult<String,User>> future = sender.send(topic,user.getName() ,user);
		
		try {
			future.get();
		}catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
