package com.amazonaws.lambda.order;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ValidateOrderHandler implements RequestHandler<Map<String,Object>, Map<String,Object>> {

    static final Logger logger = LogManager.getLogger();

    @Override
    public Map<String,Object> handleRequest(Map<String,Object> orderInput, Context context) {
    	logger.info("Inside ValidateOrderHandler {}", orderInput);

        orderInput.put("isValidated", true);
        return orderInput;
    }

}
