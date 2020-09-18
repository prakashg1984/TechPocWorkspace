package com.pg.controller;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pg.helper.TestGroovyHelper;
import com.pg.springcloud.config.RibbonConfig;

@RestController
@RequestMapping("/test")
@RefreshScope
//@RibbonClient(name="eurekaclient",configuration=RibbonConfig.class)
public class TestRestController {

	@Autowired
	TestGroovyHelper testGroovyHelper;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${welcome.message:Welcome to Infy}")
	private String welcomeMessage;
		
    @RequestMapping(value = "/post", method = RequestMethod.POST,produces={MediaType.TEXT_XML_VALUE},consumes={MediaType.TEXT_XML_VALUE} )
	public Response post1(@RequestBody String order){
		 System.out.println(order);
		 return Response.ok(order).build();
	}
    
    @RequestMapping(value = "/post2", method = RequestMethod.POST,produces={MediaType.APPLICATION_XML_VALUE},consumes={MediaType.APPLICATION_XML_VALUE} )
	public Response post2(@RequestBody String order){
		 System.out.println(order);
		 return Response.ok(order).build();
	}
    
    @RequestMapping(value = "/post3", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE} )
 	public Response post3(@RequestBody Map<String,Object> order){
    	 
 		 return Response.ok(testGroovyHelper.processOrder(order)).build();
 	}
    
    @RequestMapping(value = "/post4", method = RequestMethod.POST,produces={MediaType.ALL_VALUE},consumes={MediaType.ALL_VALUE} )
 	public Response post4(@RequestBody String order){
 		 System.out.println(order);
 		 return Response.ok(order).build();
 	}
    
    
    @RequestMapping("/")
 	public Response getCall(){ 		 
 		 return Response.ok().entity(welcomeMessage).build();
 	}
    
    @RequestMapping("/client")
 	public Response getFromRestClient(){ 	
    	 Map<String,Object> response = restTemplate.getForObject("http://EurekaClient/client/", Map.class);
 		 return Response.ok().entity(response).build();
 	}
}
