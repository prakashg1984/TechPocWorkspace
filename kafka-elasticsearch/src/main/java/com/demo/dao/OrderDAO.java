package com.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import com.demo.bo.OrderEvent;
import com.demo.bo.OrderRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Prakash_G01
 *
 */
@Repository
public class OrderDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

	private RestHighLevelClient restHighLevelClient;
	private ObjectMapper objectMapper;

	private final String ORDER_INDEX = "order";
	private final String ORDER_TYPE = "orderdata";
	
	private final String ORDER_EVENT_INDEX = "orderevent";
	private final String ORDER_EVENT_TYPE = "ordereventdata";

	public OrderDAO(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	public OrderEvent insertOrder(OrderEvent orderEvent,String customerOrderNumber) {
		 Map metaData = (Map) orderEvent.any().get("metaData");
		 Map orderData = (Map) metaData.get("originalOrder");
		
		  Map dataMap = objectMapper.convertValue(orderData, Map.class);
		  IndexRequest indexRequest = new IndexRequest(ORDER_INDEX, ORDER_TYPE, customerOrderNumber)
		                .source(dataMap);
		  try {
		    IndexResponse response = restHighLevelClient.index(indexRequest);
		    LOGGER.info("response "+response);
		  } catch(ElasticsearchException e) {
			  e.printStackTrace();
		    e.getDetailedMessage();
		  } catch (java.io.IOException ex){
			  ex.printStackTrace();
		    ex.getLocalizedMessage();
		  }	catch (Exception ex){
			  ex.printStackTrace();
			    ex.getLocalizedMessage();
		  }
		 return orderEvent;
	}
	
	public OrderRequest insertOrderEvent(OrderRequest orderRequest,String customerOrderNumber) {
		  Map dataMap = objectMapper.convertValue(orderRequest, Map.class);
		  IndexRequest indexRequest = new IndexRequest(ORDER_EVENT_INDEX, ORDER_EVENT_TYPE, customerOrderNumber)
		                .source(dataMap);
		  try {
		    IndexResponse response = restHighLevelClient.index(indexRequest);
		    LOGGER.info("response "+response);
		  } catch(ElasticsearchException e) {
		    e.getDetailedMessage();
		  } catch (java.io.IOException ex){
		    ex.getLocalizedMessage();
		  }
		 return orderRequest;
	}
	public Map<String, Object> getOrderById(String id) {
		GetRequest getRequest = new GetRequest(ORDER_INDEX, ORDER_TYPE, id);
		LOGGER.info("getRequest "+getRequest);
		GetResponse getResponse = null;
		try {
			getResponse = restHighLevelClient.get(getRequest);
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		return sourceAsMap;
	}
	
	public List<Map<String, Object>> getOrderEventsById(String id) {

		List<Map<String,Object>> responseList = new ArrayList<>();
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		try {
			MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("orderEvent.metaData.originalOrder.customerOrderNumber", id)
					.operator(Operator.AND);
			sourceBuilder.query(matchQueryBuilder);

			SearchRequest searchRequest = new SearchRequest();
			searchRequest.indices(ORDER_EVENT_INDEX);
			searchRequest.types(ORDER_EVENT_TYPE);
			searchRequest.source(sourceBuilder);
			LOGGER.info("searchRequest "+searchRequest);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			LOGGER.info("searchResponse "+searchResponse);
			for (SearchHit hit : searchResponse.getHits()) {
				responseList.add(hit.getSourceAsMap());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseList;
	
	}
	
	public List<Map<String,Object>> getOrderBySearchString(String searchString) {
		List<Map<String,Object>> orderEventList = new ArrayList<>();
		Map<String, String> searchMap = new HashMap<String, String>();

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		try {
			searchMap = objectMapper.readValue(searchString, new TypeReference<Map<String, String>>() {
			});
						
			/*MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(searchMap.get("field"), searchMap.get("value"))
					.operator(Operator.AND);*/
			BoolQueryBuilder boolQuery = new BoolQueryBuilder();
			sourceBuilder.from(searchMap.containsKey("offset") ? Integer.valueOf(searchMap.get("offset")) : 0);
			sourceBuilder.size(searchMap.containsKey("limit") ? Integer.valueOf(searchMap.get("limit")) : 10);

			searchMap.remove("offset");
			searchMap.remove("limit");
			for (Map.Entry<String, String> entry : searchMap.entrySet()){
			    boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
			}
			
			sourceBuilder.query(boolQuery);
			
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.indices(ORDER_INDEX);
			searchRequest.types(ORDER_TYPE);
			searchRequest.source(sourceBuilder);

			LOGGER.info("searchRequest "+searchRequest);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			LOGGER.info("searchResponse "+searchResponse);
			for (SearchHit hit : searchResponse.getHits()) {
				orderEventList.add(hit.getSourceAsMap());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderEventList;
	}

}
