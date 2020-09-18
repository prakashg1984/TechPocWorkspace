package com.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author Prakash_G01
 *
 */
@SpringBootApplication
@EnableKafka
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
public class SpringbootMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMainApplication.class, args);
	}
}
