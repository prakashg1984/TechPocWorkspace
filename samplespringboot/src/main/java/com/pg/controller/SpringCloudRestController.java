package com.pg.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pg.samplespringboot.SpringCloudAsyncService;
import com.pg.springcloud.config.MyFeignClient;
import com.pg.springcloud.config.RibbonConfig;

@RestController
@RequestMapping("/springCloud")
@RefreshScope
@RibbonClient(name="eurekaribbonclient",configuration=RibbonConfig.class)
public class SpringCloudRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	//Use this for Ribbon Loadbalanced RestTemplate	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	SpringCloudAsyncService springCloudAsyncService;
	
	@Autowired
	@Qualifier("nonLBRestTemplate")
	RestTemplate nonLBRestTemplate;
	
	//Use this to use Eureka discovery alone without Ribbon
	@Autowired
	DiscoveryClient discoveryClient;
	
	@Value("${welcome.message:Welcome to Infy}")
	private String welcomeMessage;
	
	@Value("${rest.client.url}")
	private String restUrl;
	
	@Autowired
	MyFeignClient myFeignClient;
    
    @RequestMapping("/")
 	public Response getCall(){ 		 
 		 return Response.ok().entity(welcomeMessage).build();
 	}
    
    @RequestMapping("/eurekaclient")
 	public Response getFromEurekaClient(){ 	
    	
    	//Use the below if we want to Use Eureka alone without Ribbon - Use the URL
    	List<ServiceInstance> serviceInstances = discoveryClient.getInstances("EUREKACLIENT");
    	ServiceInstance serviceInstance = serviceInstances.get(0);
    	URI url = serviceInstance.getUri();
    	logger.info("Client URL "+url);
    	//Map<String,Object> response = restTemplate.getForObject(url+"/client/", Map.class);
    	
    	 Map<String,Object> response = restTemplate.getForObject("http://EUREKACLIENT/client/", Map.class);
    	 response.put("Source", "SampleSpringBoot");

 		 return Response.ok().entity(response).build();
 	}
    
    @RequestMapping("/eurekaribbonclient")
 	public Response getFromRibbonClient(){ 	
    	 Map<String,Object> response = restTemplate.getForObject("http://eurekaribbonclient/client/", Map.class);
    	 response.put("Source", "SampleSpringBoot");
 		 return Response.ok().entity(response).build();
 	}
    
    @RequestMapping("/direct")
 	public Response getFromDirectClient(){ 	
    	 Map<String,Object> response = nonLBRestTemplate.getForObject(restUrl+"/client/", Map.class);
    	 response.put("Source", "SampleSpringBoot");
 		 return Response.ok().entity(response).build();
 	}
    
    
    @RequestMapping("/eurekaclientHystrix/{requestId}")
    @HystrixCommand(fallbackMethod="getClientFallBack")
 	public Response getFromEurekaClientHystrix(@PathVariable int requestId){ 	
    	
    	//Use the below if we want to Use Eureka alone without Ribbon - Use the URL
    	List<ServiceInstance> serviceInstances = discoveryClient.getInstances("EUREKACLIENT");
    	ServiceInstance serviceInstance = serviceInstances.get(0);
    	URI url = serviceInstance.getUri();
    	logger.info("Client URL "+url);
    	//Map<String,Object> response = restTemplate.getForObject(url+"/client/", Map.class);
    	logger.info("Testing for Hystrix requestId :"+requestId);
    	
    	Future<Map> responseObject=springCloudAsyncService.getClientResponse(requestId);
    	logger.info("After Get call waiting for response");
    	Map<String, Object> response = null;
		try {
			response = responseObject.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("After Get call GOT the response");
    	response.put("Source", "SampleSpringBoot");
 		return Response.ok().entity(response).build();
 	}
    
    public Response getClientFallBack(int requestId) {
    	logger.info("Testing for Hystrix Fallback");
    	Map<String,Object> responseMap = new HashMap<String,Object>();
    	responseMap.put("Status", 500);
    	responseMap.put("Message", "Error In Downstream - Please try later");
    	responseMap.put("Source", "SampleSpringBoot");
    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMap).build();
	}
    
    
    @RequestMapping("/feign/eurekaclient")
 	public Response getFromEurekaClientUsingFeign(){ 	
    	
    	
    	 Map<String,Object> response = myFeignClient.getClientResponse();
    	 response.put("Source", "SampleSpringBootFeign");

 		 return Response.ok().entity(response).build();
 	}
    
    
    @RequestMapping("/feign/eurekaclientHystrix/{requestId}")
    @HystrixCommand(fallbackMethod="getClientFallBack")
 	public Response getFromEurekaClientHystrixUsingFeign(@PathVariable int requestId){ 	
    	    	
    	Map<String,Object> response=myFeignClient.getClientResponseForId(requestId);
    	response.put("Source", "SampleSpringBootFeign");
 		return Response.ok().entity(response).build();
 	}
}
