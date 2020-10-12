package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootCommandlineApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootCommandlineApplication.class);


    public static void main(String[] args) {
    	logger.info("Starting class..");
    	SpringApplication.run(SpringbootCommandlineApplication.class, args);
    }
    
	

}
