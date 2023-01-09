package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.OrderModel;
import com.test.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private @Value("${spring.cache.redis.time-to-live.seconds}")
    long ttl;
	
	//We can use Cacheable and CachePut annotation also directly - redisTemplate is required only if we need to override that
	@Autowired
	RedisTemplate redisTemplate;
	
	//@CachePut(value = "orders", key = "#orderObject.customerOrderNumber")
	public OrderModel save(OrderModel orderObject) {
    	redisTemplate.opsForHash().put(orderObject.getCustomerOrderNumber(),orderObject.getCustomerOrderNumber().hashCode(), orderObject);
        return orderRepository.insertOrder(orderObject);
    }

	//@Cacheable(value = "orders", key = "#orderNumber")
	public OrderModel findOne(String orderNumber) {
		OrderModel orderModel = null;
		
		if (redisTemplate.opsForHash().hasKey(orderNumber, orderNumber.hashCode())) {
			logger.debug("Found on Cache : " + orderNumber);
			orderModel = (OrderModel) redisTemplate.opsForHash().get(orderNumber, orderNumber.hashCode());
			return orderModel;
		}
		 
		Map<String, Object> orderResponse = orderRepository.getOrderById(orderNumber);
		orderModel = objectMapper.convertValue(orderResponse, OrderModel.class);
		redisTemplate.opsForHash().put(orderNumber, orderNumber.hashCode(), orderModel);
		redisTemplate.expire(orderNumber, ttl, TimeUnit.SECONDS);

		return orderModel;
    }

    public List<OrderModel> findAll() {    	
    	List<OrderModel> orderModelList = null;
    	List<Map<String, Object>> searchList = orderRepository.getAllOrders();
    	if(searchList != null && searchList.size() > 0) {
    		orderModelList = new ArrayList<OrderModel>();
    		
    		for(Map<String, Object> searchData : searchList) {
    			OrderModel orderModel = objectMapper.convertValue(searchData, OrderModel.class);
    			orderModelList.add(orderModel);
    	    	redisTemplate.opsForHash().put(searchData.get("customerOrderNumber").toString(),searchData.get("customerOrderNumber").toString().hashCode(), orderModel);
    		}
    	}
    	return orderModelList;
    }

	public List<OrderModel> searchFields(String searchFields) {
    	List<OrderModel> orderModelList = null;
    	Map<String, String> searchRequest = null;
    	List<Map<String, Object>> searchList = null;
    	try {
	    	logger.info("searchFields "+searchFields);

			searchRequest = objectMapper.readValue(searchFields, Map.class);
	    	logger.info("searchRequest "+searchRequest);

		} catch (Exception e) {
			logger.error("Error in SearchFields", e);
			e.printStackTrace();
		} 
    	if(null != searchRequest && searchRequest.size() > 0) {
    		searchList = orderRepository.searchOrders(searchRequest);
    	}else {
    		searchList = orderRepository.getAllOrders();
    	}
    	if(searchList != null && searchList.size() > 0) {
    		orderModelList = new ArrayList<OrderModel>();
    		
    		for(Map<String, Object> searchData : searchList) {
    			OrderModel orderModel = objectMapper.convertValue(searchData, OrderModel.class);
    			orderModelList.add(orderModel);
    	    	redisTemplate.opsForHash().put(searchData.get("customerOrderNumber").toString(),searchData.get("customerOrderNumber").toString().hashCode(), orderModel);
    		}
    	}
    	return orderModelList;
	}
    
}
