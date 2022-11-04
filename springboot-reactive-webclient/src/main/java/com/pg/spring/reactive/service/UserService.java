package com.pg.spring.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pg.spring.reactive.model.User;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {

	@Autowired
	WebClient client;
    
	public Mono<User> getUser(String userId){
	       return client.get()
	                .uri("/users/{userId}", userId)
	                .retrieve()
	                .bodyToMono(User.class).log(" User fetched ");
	    }

	    public Flux<User> getAllUsers(){
	    	Flux<User> userResponse = client.get()
	                .uri("/users")
	                .exchangeToFlux(response -> {
	                	log.info("Fetching User after Response..");
	                    if (response.statusCode().equals(HttpStatus.OK)) {
	                        return response.bodyToFlux(User.class);
	                    }
	                    else {
	                        return response.createException().flatMapMany(Mono::error);
	                    }
	                });
	    	log.info("Completed Fetching User Request ..");
	    	return userResponse;
	    }

	    public Mono<User> createUser(User user){
	        Mono<User> userMono = Mono.just(user);
	        return client.post().uri("/users").contentType(MediaType.APPLICATION_JSON)
	                .body(userMono,User.class).retrieve().bodyToMono(User.class).log("Created User : ");

	    }

}
