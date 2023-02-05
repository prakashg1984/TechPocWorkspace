package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.demo.config"})
public class SpringbootFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFeignClientApplication.class, args);
	}

}
