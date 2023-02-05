package com.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.MyFeignClient;

@RestController
@RequestMapping("/springCloud")
@RefreshScope
public class SpringCloudRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	MyFeignClient myFeignClient;
    
    
    
    @RequestMapping("/feign/eurekaclient")
 	public ResponseEntity getFromEurekaClientUsingFeign(){ 	
    	
    	
    	 Map<String,Object> response = myFeignClient.getClientResponse();
    	 response.put("Source", "SampleSpringBootFeign");

 		 return ResponseEntity.status(HttpStatus.OK).body(response);
 	}
    
}
