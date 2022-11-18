package com.spring.azure.service;

import java.util.Iterator;
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
import com.spring.azure.util.SearchCriteria;

@Service
public class QueueMasterService {
	
	@Autowired
	@Qualifier("queueMasterContainer")
	CosmosContainer queueMasterCosmosContainer;
	
	@Autowired
	ObjectMapper objectMapper;

	public String insertQueueMaster(String queueMaster) throws Exception{
		try {
			Map<String, Object> dataMap = objectMapper.readValue(queueMaster, Map.class);
			dataMap.put("id", AzureUtil.generatUniqueIdForQueue(dataMap));
			dataMap.put("QueueId", AzureUtil.generatUniqueIdForQueue(dataMap));
	    	CosmosItemResponse item = queueMasterCosmosContainer.createItem(dataMap, new CosmosItemRequestOptions());

	    	return item.getResponseHeaders().toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}        
	}
	
	public String updateQueueMaster(String queueMaster) throws Exception{
		try {
			CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
	        //  Set populate query metrics to get metrics around query executions
	        queryOptions.setQueryMetricsEnabled(true);
	        
	        Map<String,Object> currentData = null;
			Map<String, Object> dataMap = objectMapper.readValue(queueMaster, Map.class);
			if(dataMap.containsKey("id")) {
				String pk = dataMap.get("id").toString();
				
				CosmosPagedIterable<Map> queueResponse = queueMasterCosmosContainer.queryItems(
			            "SELECT * FROM OCE_QUEUE_MASTER o where o.id = '"+pk+"'", queryOptions, Map.class);
				
				Iterator it = queueResponse.iterator();
				
				while(it.hasNext()) {
					currentData = (Map)it.next();
				}
			}
			if(null == currentData || currentData.isEmpty() || currentData.size() <0) {
				return "INVALID ID";
			}
			
	    	CosmosItemResponse item = queueMasterCosmosContainer.upsertItem(dataMap, new CosmosItemRequestOptions());

	    	return item.getResponseHeaders().toString();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Object getAllQueues() {
		CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
        
        CosmosPagedIterable<Map> queuesPagedIterable = queueMasterCosmosContainer.queryItems(
            "SELECT * FROM OCE_QUEUE_MASTER", queryOptions, Map.class);
        
        System.out.println("queuesPagedIterable "+queuesPagedIterable);
        
        return queuesPagedIterable;
	}
	
	public Object getAllQueues(String searchString) {
		CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
        
        CosmosPagedIterable<Map> queuesPagedIterable = queueMasterCosmosContainer.queryItems(
            "SELECT * FROM OCE_QUEUE_MASTER", queryOptions, Map.class);
        
        System.out.println("queuesPagedIterable "+queuesPagedIterable);
        
        return queuesPagedIterable;
	}
	
	public Object getQueuesById(String id) {
		CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
        
        CosmosPagedIterable<Map> queuesPagedIterable = queueMasterCosmosContainer.queryItems(
            "SELECT * FROM OCE_QUEUE_MASTER o where o.QueueId = '"+id+"'", queryOptions, Map.class);
        
        System.out.println("queuesPagedIterable "+queuesPagedIterable);
        
        return queuesPagedIterable;
	}
	
	public Object searchQueues(SearchCriteria criteria) {
		CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
        System.out.println("Criteria "+criteria.fields());
        CosmosPagedIterable<Map> queuesPagedIterable = queueMasterCosmosContainer.queryItems(
            "SELECT * FROM OCE_QUEUE_MASTER o where o.Channel = '"+criteria.fields().get("Channel").toString()+"'", queryOptions, Map.class);
        
        System.out.println("queuesPagedIterable "+queuesPagedIterable);
        
        return queuesPagedIterable;
	}
	
}
