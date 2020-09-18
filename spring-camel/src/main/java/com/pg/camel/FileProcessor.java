package com.pg.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileProcessor implements Processor {

    Logger log = LoggerFactory.getLogger(Processor.class.getName());

    public void process(Exchange exchange) throws Exception {
    	log.info("Processing For File "+exchange.getIn().getHeaders());
        String originalFileContent = exchange.getIn().getBody(String.class);
        String upperCaseFileContent = originalFileContent.toUpperCase();
        exchange.getIn().setBody(upperCaseFileContent);
    }

}
