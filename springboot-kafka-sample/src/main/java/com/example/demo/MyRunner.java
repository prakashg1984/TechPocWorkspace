package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.service.KafkaProducerService;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private KafkaProducerService kafkaProducerService;

	@Override
	public void run(String... args) throws Exception {
		kafkaProducerService.sendMessage("Sending New Message");
		logger.info("After Send");
	}
}
