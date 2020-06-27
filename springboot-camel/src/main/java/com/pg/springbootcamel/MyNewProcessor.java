package com.pg.springbootcamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyNewProcessor implements Processor {

	private static Logger logger = LoggerFactory.getLogger(MyNewProcessor.class);

	public void process(Exchange exchange) throws Exception {
		MyBean bodyIn = (MyBean) exchange.getIn().getBody();
		
		logger.info("bodyIn "+bodyIn.getName());
		logger.info("bodyIn "+bodyIn.getId());

        bodyIn.setId(bodyIn.getId()*10);
	}

}
