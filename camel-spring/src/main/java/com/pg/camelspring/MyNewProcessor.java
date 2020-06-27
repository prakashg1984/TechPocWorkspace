package com.pg.camelspring;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class MyNewProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange.getIn().getBody());

		File file = exchange.getIn().getBody(File.class);
		System.out.println("Processing file: " + file);
		
		String text = exchange.getIn().getBody(String.class);
		System.out.println("Processing text: " + text);

	}

}
