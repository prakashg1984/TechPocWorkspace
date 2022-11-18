package com.spring.azure.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;


public class AzureUtil {

	public static String generatUniqueIdForQueue(Map<String,Object> inputData) {
		Calendar date = Calendar.getInstance();

		String queueName = inputData.containsKey("QueueName") ? 
				inputData.get("QueueName").toString() : String.valueOf(date.getTimeInMillis()) ;
				
		String channel = inputData.get("Channel").toString();
		
		String hashCodeVal = DigestUtils.sha256Hex(queueName + ":" + channel);
		return hashCodeVal;
	}
	
	public static String generatUniqueIdForTask(Map<String,Object> inputData) {
		Calendar date = Calendar.getInstance();
				
		String customerOrderNumber = inputData.get("customerOrderNumber").toString();
		
		String hashCodeVal = DigestUtils.sha256Hex(customerOrderNumber + ":" + date.getTimeInMillis());
		return hashCodeVal;
	}
	
	
	public static void setDateValues(Map<String,Object> inputData){
		Calendar date = Calendar.getInstance();
		inputData.put("createdDate", date.getTimeInMillis());
		
		date.add(Calendar.MINUTE, 60);
		inputData.put("riskTime", date.getTimeInMillis());

		date.add(Calendar.MINUTE, 60);
		inputData.put("violationTime", date.getTimeInMillis());
		
	}
}
