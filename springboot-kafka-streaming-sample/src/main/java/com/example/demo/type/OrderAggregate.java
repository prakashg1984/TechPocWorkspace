
package com.example.demo.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count"
})
public class OrderAggregate {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    public OrderAggregate withCount(Integer count) {
        this.count = count;
        return this;
    }

   
    @Override
    public String toString() {
        return new StringBuilder("count:" +  count).toString();
    }

}
