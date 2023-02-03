package com.amazonaws.lambda.order;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DetermineFraudHandler implements RequestHandler<Map<String,Object>, Map<String,Object>> {

    static final Logger logger = LogManager.getLogger();

    @Override
    public Map<String,Object> handleRequest(Map<String,Object> orderInput, Context context) {
    	logger.info("Inside DetermineFraudHandler {}", orderInput);
        
        if(orderInput.containsKey("productSku") && orderInput.get("productSku").toString().equalsIgnoreCase("1001")) {
            orderInput.put("fraudStatus", 1);
        }else {
            orderInput.put("fraudStatus", 0);
        }
        return orderInput;
    }

}
