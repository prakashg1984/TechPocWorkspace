package com.pg.camelspring;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class MyRouteBuilder extends RouteBuilder {

    /**
     * Allow this route to be run as an application
     */
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

  /*  @Autowired
    MyNewProcessor myNewProcessor;*/
    
    public void configure() {
    	MyNewProcessor myNewProcessor = new MyNewProcessor();
        // populate the message queue with some messages
        from("file:src/data?noop=true").
                to("jms:test.MyQueue");

        from("jms:test.MyQueue").
                to("file://target/test");

        // set up a listener on the file component
        from("file://target/test?noop=true").process(myNewProcessor).
                bean(new SomeBean());
    }

    public static class SomeBean {

        public void someMethod(Exchange exchange) {
            System.out.println("Received: " + exchange.getIn().getBody().toString());
        }
    }

}

