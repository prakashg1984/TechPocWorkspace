package com.test.resource;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.service.OrderService;

@RestController
@RequestMapping("/test")
public class OrderController {
    
	@Autowired
	OrderService orderService;
	
	@Autowired
	ObjectMapper objectMapper;
    
	@RequestMapping(value = "/order", method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE} )
	public Response createOrder(@RequestBody Map<String,Object> inputObject) {

		orderService.saveOrder(inputObject);

		inputObject.put("Status", "Created");
		return Response.ok(inputObject).build();

	}
	
	@RequestMapping(value = "/order/{orderNumber}", method = RequestMethod.GET)
	public Object getOrder(@PathVariable String orderNumber) {

		Map<String,Object> orderObject = orderService.getOrder(orderNumber);

		return Response.ok().entity(orderObject).build().getEntity();

	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public Object getOrderBasedOnParam(@RequestParam("searchString") String searchString) {
		Map<String,Object> searchStringMap = null;
		System.out.println("searchString "+searchString);

		try {
			searchStringMap = objectMapper.readValue(searchString, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error in Search String").build();
		} 

		System.out.println("searchStringMap "+searchStringMap);
		Map<String,Object> orderObject = orderService.searchOrder(searchStringMap);
		return Response.ok().entity(orderObject).build().getEntity();

	}
    
}
