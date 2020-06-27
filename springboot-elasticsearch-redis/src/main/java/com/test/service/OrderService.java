package com.test.service;

import java.util.List;

import com.test.model.OrderModel;

public interface OrderService {

	OrderModel save(OrderModel orderModel);
	OrderModel findOne(String id);
    List<OrderModel> findAll();
    List<OrderModel> searchFields(String searchFields);

}
