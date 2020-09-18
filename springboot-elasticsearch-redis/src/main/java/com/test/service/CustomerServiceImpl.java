package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.CustomerModel;
import com.test.model.OrderModel;
import com.test.repository.CustomCustomerRepositoryImpl;
import com.test.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomCustomerRepositoryImpl customCustomerRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private CustomerRepository customerRepository;

	@Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
	
	@Override
	//@CachePut(value = "customers", key = "#customer.id")
	public CustomerModel save(CustomerModel customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void delete(CustomerModel customer) {
		customerRepository.delete(customer);
	}

	@Override
	//@Cacheable(value = "customers", key = "#id")
	public CustomerModel findOne(String id) {
		logger.debug("Not Found on Cache : "+id);
		Optional<CustomerModel> optionalCustModel = customerRepository.findById(id);
		if(optionalCustModel.isPresent()) {
			return optionalCustModel.get();
		}
		return null;
	}

	@Override
	public Iterable<CustomerModel> findAll() {
		return customerRepository.findAll();
	}

	public List<CustomerModel> searchFields(String searchFields) {
    	List<CustomerModel> orderModelList = null;
    	Map<String, String> searchRequest = null;
    	try {
	    	logger.info("searchFields "+searchFields);
			searchRequest = objectMapper.readValue(searchFields, Map.class);
	    	logger.info("searchRequest "+searchRequest);
		} catch (Exception e) {
			logger.error("Error in SearchFields", e);
			e.printStackTrace();
		} 
    	if(null != searchRequest && searchRequest.size() > 0) {
    		orderModelList = customCustomerRepository.searchCustomer(searchRequest);
    	}
    	return orderModelList;
	}

}
