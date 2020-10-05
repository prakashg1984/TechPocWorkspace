package com.amazonaws.lambda.order;

import java.util.Map;

import com.amazonaws.lambda.util.DynamoDBUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateOrderHandler implements RequestHandler<Map<String,Object>, Map<String,Object>> {

    static final Logger logger = LogManager.getLogger();

	DynamoDBUtil dynamoDBUtil = new DynamoDBUtil();
    @Override
    public Map<String,Object> handleRequest(Map<String,Object> orderInput, Context context) {
    	
    	logger.info("Inside CreateOrderHandler {}", orderInput);
        
        dynamoDBUtil.createOrder(orderInput);
     
        return orderInput;
    }

}
