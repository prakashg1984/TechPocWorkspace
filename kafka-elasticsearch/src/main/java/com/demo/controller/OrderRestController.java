package com.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.OrderDAO;

/**
 * @author Prakash_G01
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderRestController {

	private OrderDAO orderDAO;

    public OrderRestController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    
	@GetMapping("/{id}")
	public Map<String, Object> getOrdersById(@PathVariable String id){
	  return orderDAO.getOrderById(id);
	}
	
	@GetMapping("/event/{id}")
	public List<Map<String,Object>> getOrderEventsById(@PathVariable String id){
	  return orderDAO.getOrderEventsById(id);
	}
	
	@GetMapping
	public List<Map<String,Object>> getOrdersBySearchString(@RequestParam("searchString") String searchString){
	  return orderDAO.getOrderBySearchString(searchString);
	}
}
