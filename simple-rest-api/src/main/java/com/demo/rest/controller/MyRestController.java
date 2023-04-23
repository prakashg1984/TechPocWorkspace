package com.demo.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.model.User;
import com.demo.rest.repository.MyRepository;

@RestController
public class MyRestController {

	@Autowired
	MyRepository myRepository;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/create")
    public User createMethod(@RequestBody User user) {
        LOG.info("User Created {} ",user);
        return user;
    }
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        User user = new User();
        user.setAge("10");
        user.setName("PG");
        
        return user;
    }
}
