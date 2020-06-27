package com.test.repository;

import java.util.List;
import java.util.Map;

import com.test.model.CustomerModel;

public interface CustomCustomerRepository  {

	public List<CustomerModel> searchCustomer(Map<String,String> searchField); 
}
