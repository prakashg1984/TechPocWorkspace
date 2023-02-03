package com.pg.spring.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.spring.reactive.model.User;
import com.pg.spring.reactive.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers(){
    	log.info("Before getAllUsers");
    	Flux<User> users = userRepository.findAll().log();
    	log.info("After getAllUsers");
    	return users;
    }

    public Mono<User> findById(Integer userId){
    	log.info("Invoking findById {} ",userId);
        return userRepository.findById(userId).log();
    }

    public Mono<User> updateUser(Integer userId,  User user){
        log.info("Updating User : {} ", user);

        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(Integer userId){
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                .then(Mono.just(existingUser)));
    }

    public Flux<User> findUsersByAge(int age){
        return userRepository.findByAge(age);
    }

    public Flux<User> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .log("Fetch Users")
                .flatMap(i -> findById(i))                
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

}
