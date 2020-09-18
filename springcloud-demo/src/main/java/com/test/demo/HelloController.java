package com.test.demo;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HelloController {
    
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${message}")
	String message;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	DiscoveryClient discoveryClient;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from "+message;
    }
    
    
    @RequestMapping("/eurekaclient")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod="getUsingEurekaFallback")
	public Response getUsingEureka() {
    	
		logger.info("Inside SpringCloudDemo getUsingEureka ");

		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("EUREKACLIENT");
		if(serviceInstances.size() > 0) {
			ServiceInstance serviceInstance = serviceInstances.get(0);
			URI url = serviceInstance.getUri();
			logger.info("Client URL " + url);
		}else {
			logger.info("NO Client URL for EUREKACLIENT" );

		}
		

		Map<String, Object> response = restTemplate.getForObject("http://EUREKACLIENT/client/", Map.class);
		logger.info("response using restTemplate " + response);

		response.put("Source", "SampleSpringBoot");

		return Response.ok().entity(response).build();
	}
    
    public Response getUsingEurekaFallback() {
    	logger.info("Testing for Hystrix Fallback");
    	Map<String,Object> responseMap = new HashMap<String,Object>();
    	responseMap.put("Status", 500);
    	responseMap.put("Message", "Error In Downstream - Please try later");
    	responseMap.put("Source", "SampleSpringBoot");
    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
	}
}
