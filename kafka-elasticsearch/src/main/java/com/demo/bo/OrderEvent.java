package com.demo.bo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Prakash_G01
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEvent {

	private Map<String, Object> data = new HashMap<String, Object>();
	
	@JsonAnyGetter
	public Map<String, Object> any() {
		return data;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		this.data.put(name, value);
	}
}
