package com.spring.azure.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchCriteria {

	private Map<String,Object> searchFields = new LinkedHashMap<String,Object>();
	
	@JsonAnyGetter
	public Map<String,Object> fields() {
		return searchFields;
	}
	
	@JsonAnySetter
	public void set(String name, Object value) {
		this.searchFields.put(name, value);
	}
	
	public static SearchCriteria valueOf(String criteriaAsJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.reader().forType(SearchCriteria.class).readValue(criteriaAsJson);
	}
	
}
