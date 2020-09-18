package com.pg.controller;

import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.bo.OrderEvent;

@Repository
public class OrderDAO {

	private RestHighLevelClient restHighLevelClient;
	private ObjectMapper objectMapper;

	private final String INDEX = "orderevent";
	private final String TYPE = "order";

	public OrderDAO(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	public Map<String, Object> getOrderById(String id) {
		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = null;
		try {
			getResponse = restHighLevelClient.get(getRequest);
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		System.out.println("sourceAsMap " + sourceAsMap.toString());
		return sourceAsMap;
	}
	
	public OrderEvent insertOrder(OrderEvent orderEvent) {
		  Map dataMap = objectMapper.convertValue(orderEvent, Map.class);
		  IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, orderEvent.getCustomerOrderNumber())
		                .source(dataMap);
		  try {
		    IndexResponse response = restHighLevelClient.index(indexRequest);
		  } catch(ElasticsearchException e) {
		    e.getDetailedMessage();
		  } catch (java.io.IOException ex){
		    ex.getLocalizedMessage();
		  }
		 return orderEvent;
	}

}
