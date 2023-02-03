package com.example.demo;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SpringbootCommandlineApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootCommandlineApplication.class);


    public static void main(String[] args) {
    	logger.info("Starting class..");
    	SpringApplication.run(SpringbootCommandlineApplication.class, args);
    }

}
