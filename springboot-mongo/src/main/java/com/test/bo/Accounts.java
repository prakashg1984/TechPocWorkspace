package com.test.bo;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties(ignoreUnknown = false)
public class Accounts {
	
	/*@Indexed(unique = true)
	private String orderNumber;*/
	
	private String orderNumber;
	
	private Map<String, Object> map = new HashMap<String, Object>();

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return map;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		this.map.put(name, value);
	}
}
