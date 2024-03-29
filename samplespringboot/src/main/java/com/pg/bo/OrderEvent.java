package com.pg.bo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEvent {

	@JsonProperty
	private String customerOrderNumber;

	private Map<String, Object> data = new HashMap<String, Object>();
	
	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}

	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return data;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		this.data.put(name, value);
	}
}
