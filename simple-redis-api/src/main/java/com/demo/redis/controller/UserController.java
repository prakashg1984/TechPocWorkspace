package com.demo.redis.controller;

import com.demo.redis.repository.PersonRepository;
import com.demo.redis.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final PersonRepository personRepository;

    @Autowired
    public UserController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/create")
    public Person createPerson(@RequestBody Person user) {
        user = personRepository.save(user);
        LOG.info("User Created {} ",user);
        redisTemplate.boundValueOps(user.getId().toString()).set(user);
        return user;
    }

    @Cacheable(value = "users", key = "#userId")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Person getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        return personRepository.findById(Long.valueOf(userId)).get();
    }

    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/update")
    public Person updatePersonByID(@RequestBody Person user) {
    	personRepository.save(user);
        return user;
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("/{userId}")
    public void deleteUserByID(@PathVariable Long userId) {
        LOG.info("deleting person with id {}", userId);
        personRepository.deleteById(userId);
    }
}
