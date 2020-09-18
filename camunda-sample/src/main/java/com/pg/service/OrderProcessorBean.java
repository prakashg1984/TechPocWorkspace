package com.pg.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.pg.bo.OrderEventRequest;

@Component("orderProcessorBean")
public class OrderProcessorBean {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "RouteEventProducerTemplate")
	private KafkaTemplate<String, OrderEventRequest> sender;
	
	@Value("${order.event.topic}")
	private String orderEventTopic;
	
	public boolean validateOrder(OrderEventRequest eventRequest) {
		logger.info("inside validateOrder "+ eventRequest.toString());
		String customerOrderNr = ((Map<String,Object>) eventRequest.any().get("event")).get("customerOrderNumber").toString();
		
		if(customerOrderNr.startsWith("22-")) {
			return true;
		}else {
			return false;
		}
	}
}
