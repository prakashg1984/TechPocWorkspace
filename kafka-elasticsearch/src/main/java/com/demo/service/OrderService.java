package com.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bo.OrderEvent;
import com.demo.bo.OrderRequest;
import com.demo.dao.OrderDAO;

@Service
public class OrderService {

	@Autowired
	OrderDAO orderDAO;
	
	public void insertOrderAndEvent(OrderRequest orderRequest) {
		
		OrderEvent orderEvent = orderRequest.getOrderEvent();
		Map metaData = (Map) orderEvent.any().get("metaData");
		String customerOrderNumber = metaData.get("customerOrderNumber").toString();
		String eventId = orderEvent.any().get("eventid").toString();
		
		orderDAO.insertOrder(orderEvent, customerOrderNumber);
		orderDAO.insertOrderEvent(orderRequest, eventId);
		
	}
}


