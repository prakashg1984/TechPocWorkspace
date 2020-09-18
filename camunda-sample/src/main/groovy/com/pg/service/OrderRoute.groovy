package com.pg.service

import javax.annotation.Resource

import org.apache.camel.Exchange
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

import com.pg.bo.OrderEventRequest

@Component("orderRoute")
class OrderRoute {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "RouteEventProducerTemplate")
	private KafkaTemplate<String, OrderEventRequest> sender;
		
	public void processEvent(Exchange exchange) {
		Map<String, Object> exchangeVariables = (Map<String, Object>) exchange.getIn().getBody();
		logger.info("exchangeVariables.get() {} " , exchangeVariables.get("eventRequest").toString());
		
		def eventRequest = exchangeVariables.get("eventRequest");
		def customerOrderNr =  eventRequest.data.event.customerOrderNumber;
		
		sender.send("ORDER_EVENT_TOPIC",
				customerOrderNr , eventRequest);
		
		exchange.getOut().setBody(exchangeVariables.get("eventRequest"));
	}
	
	
	public void createEvent(def customerOrderNr,def eventRequest) {
		sender.send("ORDER_EVENT_TOPIC",
			customerOrderNr , eventRequest);
	}
}
