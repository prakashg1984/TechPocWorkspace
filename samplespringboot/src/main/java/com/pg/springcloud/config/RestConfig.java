package com.pg.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

@Configuration
public class RestConfig {

	@Bean 
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean(name="nonLBRestTemplate") 
	RestTemplate nonLBRestTemplate() {
		return new RestTemplate();
	}

/*	@Bean
	  public HystrixCommandAspect hystrixAspect() {
	    return new HystrixCommandAspect();
	  }*/
}
