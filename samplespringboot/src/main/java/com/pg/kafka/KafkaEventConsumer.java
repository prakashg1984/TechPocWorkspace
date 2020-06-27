package com.pg.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pg.bo.OrderEvent;
import com.pg.controller.OrderDAO;

@Component
@EnableKafka
public class KafkaEventConsumer {

	@Autowired
	OrderDAO orderDAO;
	
	@KafkaListener(topics = "${test.event.topic:TEST_ES_EVENT_TOPIC}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveEvent(OrderEvent eventRequest) {
		System.out.println("Received eventRequest "+eventRequest.toString());
		orderDAO.insertOrder(eventRequest);
	}
}
