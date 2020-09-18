package com.example.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class BatchProcessingApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BatchProcessingApplication.class, args);
	}
	
	@Bean
	BatchConfiguration batchConfigurationService(){
        return new BatchConfiguration();
    }
}
