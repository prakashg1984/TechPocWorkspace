
package com.example.demo.type;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

	 private Map<String, Object> any = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAny() {
    	return any;
    }
    
    @JsonAnySetter
    public void setAddress(String name, Object value) {
    	this.any.put(name, value);
    }

}
