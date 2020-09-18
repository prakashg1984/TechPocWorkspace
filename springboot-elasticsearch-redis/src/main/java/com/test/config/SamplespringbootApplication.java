package com.test.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "com.test.repository")
public class SamplespringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplespringbootApplication.class, args);
	}
}
