package com.amazonaws.lambda.address;

import java.util.UUID;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;


public class AddressGetHandler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input);
        AddressGetHandler lambdaFunctionHandler = new AddressGetHandler();
        
        String addressObject = lambdaFunctionHandler.dynamoConnect(input);
        
        return addressObject;
       
    }
    
    private String dynamoConnect(String input){
    	/* AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
         		new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-2"))
         		.build();*/
    	 
    	 AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    			 .withRegion(Regions.US_EAST_2)
    			 .build();
    	 
    	 DynamoDB dynamoDB = new DynamoDB(client);
         
         Table table = dynamoDB.getTable("Address");
                 
         Item item = table.getItem("id" , input);
         return item.toJSONPretty();
    }

}
