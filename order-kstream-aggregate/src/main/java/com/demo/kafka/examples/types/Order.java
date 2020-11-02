
package com.demo.kafka.examples.types;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

	 private Map<String, String> any = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getAny() {
    	return any;
    }
    
    @JsonAnySetter
    public void setAddress(String name, String value) {
    	this.any.put(name, value);
    }

}
