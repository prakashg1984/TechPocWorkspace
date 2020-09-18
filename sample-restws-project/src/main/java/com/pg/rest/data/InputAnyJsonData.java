package com.pg.rest.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class InputAnyJsonData {

	private Map<String, Object> map = new HashMap<String, Object>();
	
	@JsonAnyGetter
	public Map<String, Object> any() {
		return map;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		this.map.put(name, value);
	}
}
