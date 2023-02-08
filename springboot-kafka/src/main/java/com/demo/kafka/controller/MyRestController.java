package com.demo.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.kafka.model.User;
import com.demo.kafka.service.MyKafkaService;

@RestController
public class MyRestController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MyKafkaService myKafkaService;
	
	@PostMapping("/create")
    public User createMethod(@RequestBody User user) {
		myKafkaService.publishToKafka(user);
        LOG.info("User Published to kafka {} ",user);
        return user;
    }
	
}
