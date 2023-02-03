package com.example.demo.kafka.consumer;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
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
	
	
	@KafkaListener(topics = "WordsWithCountsTopic", containerFactory = "kafkaListenerContainerFactoryLong")
	public void receiveEventLong(Long value, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
		System.out.println("Received Key "+key + " , Value "+value);
	}
}
