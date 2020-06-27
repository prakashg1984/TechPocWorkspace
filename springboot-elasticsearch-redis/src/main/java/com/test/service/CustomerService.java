package com.test.service;

import java.util.List;

import com.test.model.CustomerModel;

public interface CustomerService {

	CustomerModel save(CustomerModel book);

    void delete(CustomerModel book);

    CustomerModel findOne(String id);

    Iterable<CustomerModel> findAll();

    List<CustomerModel> searchFields(String searchFields);

}
