package com.test.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.test.model.CustomerModel;

public interface CustomerRepository extends ElasticsearchRepository<CustomerModel, String>  {

}
