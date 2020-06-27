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


public class AddressCreateHandler implements RequestHandler<AddressBO, String> {

    @Override
    public String handleRequest(AddressBO input, Context context) {
        context.getLogger().log("Input: " + input);

        AddressCreateHandler lambdaFunctionHandler = new AddressCreateHandler();
        
        String addressId = lambdaFunctionHandler.dynamoConnect(input);
        
        return "SuccessFully Inserted Address with ID : "+addressId;
       
    }
    
    private String dynamoConnect(AddressBO input){
    	/* AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
         		new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-2"))
         		.build();*/
    	 
    	 AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    			 .withRegion(Regions.US_EAST_2)
    			 .build();
    	 
    	 DynamoDB dynamoDB = new DynamoDB(client);

    	 final String uuid = UUID.randomUUID().toString().replace("-", "");

         
         Table table = dynamoDB.getTable("Address");
         
         table.putItem(new Item().withPrimaryKey("id",uuid)
        		 .with("city", input.getCity())
        		 .with("country" , input.getCountry())
        		 .with("locality" , input.getLocality())
        		 .with("postcode" , input.getPostcode())
        		 .with("stateOrProvince" , input.getStateOrProvince())
        		 .with("streetName" , input.getStreetName())
        		 .with("streetNr" , input.getStreetNr())
        		 .with("streetNrLast" , input.getStreetNrLast())
        		 .with("streetNrLastSuffix" , input.getStreetNrLastSuffix())
        		 .with("streetNrSuffix" , input.getStreetNrSuffix())
        		 .with("streetSuffix" , input.getStreetSuffix())
        		 .with("streetType" , input.getStreetType()));
         
         return uuid;
    }

}
