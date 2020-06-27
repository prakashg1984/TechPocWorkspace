package com.pg.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.pg.processor.MessageProcessor;
import com.pg.service.OrderRoute;
import com.pg.service.PrintMessageBean;

@Component
public class ProcessRouteBuilder extends RouteBuilder {

	 @Override
	 public void configure() throws Exception {
		 
	    from("activemq:START_QUEUE")
	    .bean(MessageProcessor.class, "startInstance")
	    .routeId("START_QUEUE");
	    
	    
	    from("activemq:END_QUEUE")
	    .bean(MessageProcessor.class, "endInstance")
	    .routeId("END_QUEUE");
	    
	    
	    from("direct:printmessage")
	    .bean(PrintMessageBean.class, "printMessage")
	    .routeId("printmessage");
	    
	    
	    from("direct:createEvent")
	    .bean(OrderRoute.class, "processEvent")
	    .log("Inside direct:createEvent")
	    .routeId("processEvent");
	    
	 }
}
