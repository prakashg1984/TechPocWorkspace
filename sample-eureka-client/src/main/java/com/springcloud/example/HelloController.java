package com.springcloud.example;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
	Logger logger = LoggerFactory.getLogger(this.getClass());

	Map<String,Object> responseMap;
	
    @RequestMapping("/client")
    public Object getClient() {
    	logger.info("Inside getClient :");

    	responseMap = new HashMap();
    	responseMap.put("Status", 200);
    	responseMap.put("Message", "Success");
    	responseMap.put("Source", "EurekaClient");
        return Response.ok().entity(responseMap).build().getEntity();
    }
    
    @RequestMapping("/client/hystrix/{requestId}")
    public Object getClientHystrix(@PathVariable int requestId) {
    	
    	logger.info("Inside getClientHystrix :"+requestId);
    	if(requestId == 123) {
    		throw new RuntimeException();
    	}
    	
    	if(requestId == 234) {
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	responseMap = new HashMap();
    	responseMap.put("Status", 200);
    	responseMap.put("Message", "Success");
    	responseMap.put("Source", "EurekaClient");
        return Response.ok().entity(responseMap).build().getEntity();
    }
}
