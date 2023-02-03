package com.example.demo.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    static Logger logger = LoggerFactory.getLogger(KafkaEventConsumer.class);

	@KafkaListener(topics = "${kafka.topic:SAMPLE_DEV_TOPIC2}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveEvent(String orderRequest) {
		logger.info("Received OrderRequest "+orderRequest);
	}
}
