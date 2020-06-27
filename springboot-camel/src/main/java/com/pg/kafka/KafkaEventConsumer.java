package com.pg.kafka;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pg.springbootcamel.MyBean;

@Component
@EnableKafka
public class KafkaEventConsumer {

	private static Logger logger = LoggerFactory.getLogger(KafkaEventConsumer.class);

	private ProducerTemplate producer;
	
	@Autowired
	private CamelContext camelContext;
	
	@KafkaListener(topics = "${test.event.topic:TEST_ES_EVENT_TOPIC}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveEvent(MyBean eventRequest) {
		logger.info("Received eventRequest "+eventRequest.toString());
		
		this.producer = camelContext.createProducerTemplate();
		this.producer.setDefaultEndpoint(camelContext.getEndpoint("direct:invoke:invokeroute"));
		
		this.producer.sendBody(eventRequest);
	}
}
