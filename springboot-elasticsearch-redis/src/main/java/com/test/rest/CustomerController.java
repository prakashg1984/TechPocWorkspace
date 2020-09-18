package com.test.rest;

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

import com.test.model.CustomerModel;
import com.test.model.OrderModel;
import com.test.service.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerServiceImpl customerService;
    
    @RequestMapping(method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE} )
	public Response postCustomer(@RequestBody Map<String,Object> customerRequest){
    	CustomerModel customerModel = new CustomerModel();
    	customerModel.setId(customerRequest.get("customerId").toString());
    	customerModel.setCustomerData(customerRequest);
    	customerModel = customerService.save(customerModel);
		logger.info("Got Response postCustomer : "+customerModel.toString());
		return Response.ok(customerModel).build();
	}
    
    @RequestMapping(value="/{id}" ,method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE} )
  	public Response getCustomer(@PathVariable String id){
  		 
    	CustomerModel customerModel = customerService.findOne(id);
    	logger.info("Got Response getCustomer : "+customerModel);
 
  		return Response.status(Status.OK).entity(customerModel).build();
  	}
    
    @RequestMapping(value="/find/all",  method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE} )
  	public Object getAllCustomers(){
  		 
  		 Iterable<CustomerModel> customerModelList = customerService.findAll();
		 logger.info("Got Response getAllOrders : "+customerModelList);

  		 return Response.ok(customerModelList).build().getEntity();
  	}
    
    @RequestMapping(value="/search",  method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE} )
  	public Object searchOrder(@RequestParam(name = "searchFields") String searchFields){
  		 List<CustomerModel> customerModelList = customerService.searchFields(searchFields);
		 logger.info("Got Response searchOrder : "+customerModelList);

  		 return Response.ok(customerModelList).build().getEntity();
  	}
}
