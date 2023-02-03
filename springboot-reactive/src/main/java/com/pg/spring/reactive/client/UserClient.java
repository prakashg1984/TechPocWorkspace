package com.pg.spring.reactive.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pg.spring.reactive.model.User;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserClient {
	static Logger logger = LoggerFactory.getLogger(UserClient.class);
    private WebClient client = WebClient.create("http://localhost:8091");
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
                	logger.info("Fetching User ..");
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(User.class);
                    }
                    else {
                        return response.createException().flatMapMany(Mono::error);
                    }
                });
    	logger.info("Completed Fetching User ..");
    	return userResponse;
               // .exchange().flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class)).log("Users Fetched : ");
    }

    public Mono<User> createUser(User user){
        Mono<User> userMono = Mono.just(user);
        return client.post().uri("/users").contentType(MediaType.APPLICATION_JSON)
                .body(userMono,User.class).retrieve().bodyToMono(User.class).log("Created User : ");

    }


}
