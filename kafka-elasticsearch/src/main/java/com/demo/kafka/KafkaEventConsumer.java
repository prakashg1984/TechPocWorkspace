package com.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.demo.bo.OrderRequest;
import com.demo.service.OrderService;

/**
 * @author Prakash_G01
 *
 */
@Component
@EnableKafka
public class KafkaEventConsumer {

	@Autowired
	OrderService orderService;
	
	@KafkaListener(topics = "${test.event.topic:TEST_ES_EVENT_TOPIC}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveEvent(OrderRequest orderRequest) {
		System.out.println("Received OrderRequest "+orderRequest.toString());
		orderService.insertOrderAndEvent(orderRequest);
	}
}
