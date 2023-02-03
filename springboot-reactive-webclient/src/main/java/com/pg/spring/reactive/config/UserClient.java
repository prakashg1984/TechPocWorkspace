package com.pg.spring.reactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserClient {
	
	@Value("${user.rest.api}")
	private String clientUrl;    
    
    @Bean
    public WebClient createWebClient() {
    	WebClient client = WebClient.create(clientUrl);
    	return client;
    }
    
}
