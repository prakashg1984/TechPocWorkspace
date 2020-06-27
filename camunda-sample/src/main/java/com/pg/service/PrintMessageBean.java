package com.pg.service;

import java.util.Map;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("printMessageBean")
public class PrintMessageBean {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void printMessage(Exchange exchange) {
		logger.info("inside printMessage "+ exchange.getIn().getBody());
		
		Map<String, Object> exchangeVariables = (Map<String, Object>) exchange.getIn().getBody();
		
		exchange.getOut().setBody("HALO "+exchangeVariables.get("message"));
	}
	
	
	public String doWork(String message) {
		logger.info("inside doWork "+ message);
		return "Final "+message;
	}
	
	public String doDefaultWork(String message) {
		logger.info("inside doDefaultWork "+ message);
		return "Final Default "+message;
	}
	
	public boolean checkMessage(String message) {
		logger.info("checkMessage "+ message);
		if(message.contains("prakash")) {
			return true;
		}else {
			return false;
		}
	}
}
