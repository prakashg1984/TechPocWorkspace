package com.amazonaws.lambda.order;

import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynamoDBUtil {
	
    static final Logger logger = LogManager.getLogger();

	DynamoDB dynamoDB = null;
	public DynamoDBUtil() {
    	logger.info("Start DynamoDBUtil {} ",dynamoDB);

		 AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    			 .withRegion(Regions.US_EAST_2)
    			 .build();
    	 if(null == dynamoDB) {
        	 dynamoDB = new DynamoDB(client);
    	 }
    	 
     	logger.info("End DynamoDBUtil {} ",dynamoDB);
	}
	
	public void closeDBConnection() {
		if(null != dynamoDB) {
			dynamoDB.shutdown();
			dynamoDB = null;
		}
	}
	
	public void createOrder(Map<String,Object> orderInput) {
		if(null != dynamoDB) {
	    	 final String uuid = UUID.randomUUID().toString().replace("-", "");
	         
	         Table table = dynamoDB.getTable("OrderDetails");
	         
	         table.putItem(new Item().withPrimaryKey("id",uuid)
	        		 .with("orderJson", orderInput));
	         
	         if(orderInput.containsKey("ValidationStatus") && 
	        		 Integer.valueOf(orderInput.get("ValidationStatus").toString()) == 0) {
		         table = dynamoDB.getTable("OrderFalloutDetails");
		         table.putItem(new Item().withPrimaryKey("id",uuid)
		        		 .with("fallOutJson", orderInput));
	         }
		}
		
		closeDBConnection();
	}
	
}
