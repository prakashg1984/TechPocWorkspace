package com.test.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "customerevent", type = "customers")
public class CustomerModel {

	 @Id
	 private String id;
	 private Map<String,Object> customerData;
	 
	 public CustomerModel() {
		 
	 }
	 
	 public CustomerModel(String id, Map<String,Object> customerData) {
		 this.id = id;
		 this.customerData = customerData;
	 }

	@Override
	public String toString() {
		return "CustomerModel [id=" + id + ", customerData=" + customerData + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getCustomerData() {
		return customerData;
	}

	public void setCustomerData(Map<String, Object> customerData) {
		this.customerData = customerData;
	}
}
