package com.demo.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.demo.kafka.model.User;

@Component
@EnableKafka
public class KafkaEventConsumer {

    static Logger logger = LoggerFactory.getLogger(KafkaEventConsumer.class);

	@KafkaListener(topics = "${kafka.topic:SAMPLE_DEMO_TOPIC}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveEvent(User user) {
		logger.info("Received User Message "+user);
	}
}
