package com.demo.bo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class OrderRequest {

	private Map<String, Object> data = new HashMap<String, Object>();

	@JsonProperty
	private OrderEvent orderEvent;

	
	public OrderEvent getOrderEvent() {
		return orderEvent;
	}

	public void setOrderEvent(OrderEvent orderEvent) {
		this.orderEvent = orderEvent;
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
