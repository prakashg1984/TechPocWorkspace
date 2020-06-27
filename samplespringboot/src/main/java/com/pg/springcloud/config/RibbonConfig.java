package com.pg.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;

public class RibbonConfig {

	@Autowired
	IClientConfig clientconfig;
	
	@Bean
	public IPing changePing(IClientConfig clientconfig){
	return new PingUrl();	
	}

}
