package com.test.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.OrderModel;
import com.test.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;
	
	@Autowired
	ObjectMapper objectMapper;
	
    @RequestMapping("/halo")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE} )
	public Response postOrder(@RequestBody OrderModel orderObject){		 
		 orderObject = orderService.save(orderObject);
		 logger.info("Got Response postOrder : "+orderObject);
		 return Response.ok(orderObject).build();
	}
    
    @RequestMapping(value="/{id}" ,method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE} )
  	public Response getOrder(@PathVariable String id){
  		 
    	OrderModel orderObject = orderService.findOne(id);
    	logger.info("Got Response getOrder : "+orderObject);
 
  		return Response.status(Status.OK).entity(orderObject).build();
  	}
    
    @RequestMapping(value="/find/all",  method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE} )
  	public Object getAllOrders(){
  		 
  		 List<OrderModel> orderObjectList = orderService.findAll();
		 logger.info("Got Response getAllOrders : "+orderObjectList);

  		 return Response.ok(orderObjectList).build().getEntity();
  	}
    
    
    @RequestMapping(value="/search",  method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE} )
  	public Object searchOrder(@RequestParam(name = "searchFields") String searchFields){
  		 List<OrderModel> orderObjectList = orderService.searchFields(searchFields);
		 logger.info("Got Response getAllOrders : "+orderObjectList);

  		 return Response.ok(orderObjectList).build().getEntity();
  	}
}
