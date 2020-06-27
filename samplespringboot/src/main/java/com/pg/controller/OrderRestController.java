package com.pg.controller;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

	private OrderDAO orderDAO;

    public OrderRestController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    
	@GetMapping("/{id}")
	public Map<String, Object> getBookById(@PathVariable String id){
	  return orderDAO.getOrderById(id);
	}
	
    @RequestMapping(value = "/post", method = RequestMethod.POST,produces={MediaType.TEXT_PLAIN_VALUE},consumes={MediaType.TEXT_PLAIN_VALUE} )
	public Response postBook(@RequestBody String order){
		 System.out.println(order);
		 return Response.ok(order).build();
	}
    
    @RequestMapping(value = "/json", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},headers = "Accept=application/json")
   	public Response postBookJson(@RequestBody Object order){
   		 System.out.println(order);
   		 return Response.ok(order).build();
   	}
}
