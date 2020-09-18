package com.pg.bo;

import java.io.Serializable;
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
public class OrderEventRequest implements Serializable {

	private static final long serialVersionUID = 1L;
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
