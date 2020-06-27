package com.pg.springbootcamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MySecondProcessor implements Processor {

	private static Logger logger = LoggerFactory.getLogger(MySecondProcessor.class);

	@Autowired
	ObjectMapper objectMapper;
	
	public void process(Exchange exchange) throws Exception {
		MyBean bodyIn = (MyBean) exchange.getIn().getBody();
		
        logger.info("bodyIn "+bodyIn.getId());
		exchange.getIn().setBody(objectMapper.writeValueAsString(bodyIn));
	}

}
