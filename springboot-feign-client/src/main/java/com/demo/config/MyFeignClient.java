package com.demo.config;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("EUREKACLIENT")
public interface MyFeignClient {

	@RequestMapping(value="/client")
	Map<String,Object> getClientResponse();
	
	@RequestMapping(value="/client/hystrix/{requestId}")
	Map<String,Object> getClientResponseForId(@PathVariable("requestId") int requestId);
}
