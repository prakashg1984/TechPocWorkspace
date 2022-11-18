package com.spring.azure.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.azure.util.AzureUtil;

@Service
public class TaskQueueService {
	
	@Autowired
	@Qualifier("taskDetailsContainer")
	CosmosContainer taskCosmosContainer;
	
	@Autowired
	ObjectMapper objectMapper;

	public String insertTask(String task) {
		try {
			Map<String, Object> dataMap = objectMapper.readValue(task, Map.class);
			dataMap.put("id", AzureUtil.generatUniqueIdForTask(dataMap));
			dataMap.put("taskId", AzureUtil.generatUniqueIdForTask(dataMap));
			
			AzureUtil.setDateValues(dataMap);
			
	    	CosmosItemResponse item = taskCosmosContainer.createItem(dataMap, new CosmosItemRequestOptions());

	    	return item.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ERROR";
        
	}
	
	public String updateTask(String task) {
		try {
			Map<String, String> dataMap = objectMapper.readValue(task, Map.class);

	    	CosmosItemResponse item = taskCosmosContainer.upsertItem(dataMap, new CosmosItemRequestOptions());

	    	return item.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "ERROR";
        
	}
	
	public Object getAllTasks() {
		CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
        
        CosmosPagedIterable<Map> familiesPagedIterable = taskCosmosContainer.queryItems(
            "SELECT * FROM OCE_TASK_DETAILS", queryOptions, Map.class);
        
        System.out.println("familiesPagedIterable "+familiesPagedIterable);
        
        return familiesPagedIterable;
	}
	
	public Object getTaskById(String id) {
		CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
        
        CosmosPagedIterable<Map> familiesPagedIterable = taskCosmosContainer.queryItems(
            "SELECT * FROM OCE_TASK_DETAILS o where o.taskid = '"+id+"'", queryOptions, Map.class);
        
        System.out.println("familiesPagedIterable "+familiesPagedIterable);
        
        return familiesPagedIterable;
	}
}
