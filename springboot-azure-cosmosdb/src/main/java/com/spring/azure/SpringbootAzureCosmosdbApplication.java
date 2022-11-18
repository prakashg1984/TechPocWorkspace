package com.spring.azure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAzureCosmosdbApplication{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootAzureCosmosdbApplication.class);

	/*
	 * @Autowired private TaskRepository repository;
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAzureCosmosdbApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) { //
	 * this.repository.deleteAll().block();
	 * LOGGER.info("Deleted all data in container.");
	 * 
	 * final Task task = new Task("Task101","23-1212121212","DE-MOBILITY","NEW");
	 * 
	 * // Save the User class to Azure Cosmos DB database. final Mono<Task>
	 * saveUserMono = repository.save(task); final Task savedTask =
	 * saveUserMono.block();
	 * 
	 * LOGGER.info("Saving data {} ",savedTask.getTaskId());
	 * 
	 * final Optional<Task> optionalTaskResult =
	 * repository.findById("Task123").blockOptional();
	 * 
	 * final Task result = optionalTaskResult.get();
	 * 
	 * LOGGER.info("Loading data {} ",result.toString());
	 * 
	 * 
	 * }
	 */
}
