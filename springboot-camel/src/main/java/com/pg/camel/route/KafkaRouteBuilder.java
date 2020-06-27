package com.pg.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.pg.springbootcamel.MyNewProcessor;
import com.pg.springbootcamel.MySecondProcessor;

@Component
public class KafkaRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:invoke:invokeroute")
		.bean(MyNewProcessor.class)
		.log("Inside Route ${body.name}")
		.bean(MySecondProcessor.class)
		.convertBodyTo(String.class)
		.to("file:C://outputFolder");
	}

}
