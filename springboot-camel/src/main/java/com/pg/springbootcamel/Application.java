package com.pg.springbootcamel;

import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages="com.pg")
public class Application{

    @Value("${server.port}")
    String serverPort;
    
    @Value("${api.path}")
    String contextPath;
    
    @Autowired
    MyNewProcessor myNewProcessor;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath+"/*");
        servlet.setName("CamelServlet");
        return servlet;
    }


    @Component
    class RestApi extends RouteBuilder {

        @Override
        public void configure() {

            CamelContext context = new DefaultCamelContext();

            
            // http://localhost:8080/camel/api-doc GET
            restConfiguration().contextPath(contextPath) //
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiProperty("cors", "true") // cross-site
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

            
         // http://localhost:8080/camel/api/bean POST
            rest("/api/").description("Teste REST Service")
                .id("api-route")
                .post("/bean")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .bindingMode(RestBindingMode.auto)
                .type(MyBean.class)
                .enableCORS(true)
                .to("direct:remoteService");
            
       
            from("direct:remoteService")
                .routeId("direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        MyBean bodyIn = (MyBean) exchange.getIn().getBody();
                        
                        ExampleServices.example(bodyIn);

                        exchange.getIn().setBody(bodyIn);
                    }
                })
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .process(myNewProcessor)
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
        }
    }
}
