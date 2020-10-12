package com.example.demo.kafka.consumer;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Prakash_G01
 *
 */
@Component
@EnableKafka
public class KafkaEventConsumer {

	
	@KafkaListener(topics = "${kafka.topic:SAMPLE_DEV_TOPIC2}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveEvent(String orderRequest) {
		System.out.println("Received OrderRequest "+orderRequest);
	}
}
