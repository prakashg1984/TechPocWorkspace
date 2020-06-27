package com.test.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.OrderModel;

@Repository
public class OrderRepository {

	private static Logger logger = LoggerFactory.getLogger(OrderRepository.class);

	private RestHighLevelClient restHighLevelClient;
	private ObjectMapper objectMapper;

	private final String INDEX = "orderevent";
	private final String TYPE = "order";

	public OrderRepository(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	public OrderModel insertOrder(OrderModel orderModel) {
		Map dataMap = objectMapper.convertValue(orderModel, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, orderModel.getCustomerOrderNumber()).source(dataMap);
		try {
			IndexResponse response = restHighLevelClient.index(indexRequest);
		} catch (ElasticsearchException e) {
			e.getDetailedMessage();
		} catch (java.io.IOException ex) {
			ex.getLocalizedMessage();
		}
		return orderModel;
	}

	public Map<String, Object> getOrderById(String id) {
		logger.debug("Not found in cache...Going to DB : " + id);
		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = null;
		try {
			getResponse = restHighLevelClient.get(getRequest);
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		return sourceAsMap;
	}

	public List<Map<String, Object>> getAllOrders() {
		List<Map<String, Object>> searchResponseList = new ArrayList<Map<String, Object>>();
		SearchRequest searchRequest = new SearchRequest(INDEX);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);

		System.out.println("searchRequest "+searchRequest);

		try {
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHits searchHits = searchResponse.getHits();

			for (SearchHit searchHit : searchHits.getHits()) {
				searchResponseList.add(searchHit.getSourceAsMap());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchResponseList;
	}
	
	
	public List<Map<String, Object>> searchOrders(Map<String,String> searchField) {
		List<Map<String, Object>> searchResponseList = new ArrayList<Map<String, Object>>();
		SearchRequest searchRequest = new SearchRequest(INDEX);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : searchField.entrySet()){
		    boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
		}
		System.out.println("boolQuery "+boolQuery);

		searchSourceBuilder.query(boolQuery);
		searchRequest.source(searchSourceBuilder);

		System.out.println("searchRequest "+searchRequest);
		try {
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHits searchHits = searchResponse.getHits();

			for (SearchHit searchHit : searchHits.getHits()) {
				searchResponseList.add(searchHit.getSourceAsMap());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchResponseList;
	}
}
