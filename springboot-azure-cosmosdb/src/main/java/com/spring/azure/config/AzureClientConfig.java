package com.spring.azure.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.UniqueKeyPolicy;

@Configuration
public class AzureClientConfig {
	
	@Value("${spring.cloud.azure.cosmos.endpoint}")
	private String hostName;  
	
	@Value("${spring.cloud.azure.cosmos.key}")
	private String secretKey;  
	
	@Value("${spring.cloud.azure.cosmos.database}")
	private String databaseName;   
	
	@Value("${cosmos.database.taskdetails.container}")
	private String taskDetailsContainerName; 
	
	@Value("${cosmos.database.queuemaster.container}")
	private String queueMasterContainerName; 
	
	@Autowired
	CosmosClient client;
	
	@Autowired
	CosmosDatabase database;
	
	@Bean
	public CosmosClient createCosmosClient() {
		ArrayList<String> preferredRegions = new ArrayList<String>();
        preferredRegions.add("West US");

        //  Create sync client
        client = new CosmosClientBuilder()
            .endpoint(hostName)
            .key(secretKey)
            .preferredRegions(preferredRegions)
            .userAgentSuffix("CosmosDBJavaQuickstart")
            .consistencyLevel(ConsistencyLevel.EVENTUAL)
            .buildClient();
        
        return client;
	}
	
	@Bean
	public CosmosDatabase createCosmosDatabase() {
		System.out.println("Creating database");
	//  Create database if not exists
        CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);
        database = client.getDatabase(databaseResponse.getProperties().getId());
        
        return database;
	}

	@Bean(name = "taskDetailsContainer")
	public CosmosContainer createCosmosContainer() {
		CosmosContainerProperties containerProperties =
	            new CosmosContainerProperties(taskDetailsContainerName, "/taskId");
		
		System.out.println("Database1 "+database);
		System.out.println("containerProperties1 "+containerProperties);
		
        CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
        CosmosContainer container = database.getContainer(containerResponse.getProperties().getId());
        
        return container;
	}
	
	
	@Bean(name = "queueMasterContainer")
	public CosmosContainer createQueueMasterCosmosContainer() {
		CosmosContainerProperties containerProperties = new CosmosContainerProperties(queueMasterContainerName,
				"/QueueId");

		System.out.println("Database2 " + database);
		System.out.println("containerProperties2 " + containerProperties);

		CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
		CosmosContainer container = database.getContainer(containerResponse.getProperties().getId());

		return container;
	}
	 

}
