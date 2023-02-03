package com.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.demo.kafka.examples.serde.JsonDeserializer;
import com.demo.kafka.examples.serde.JsonSerializer;
import com.demo.kafka.examples.types.Order;
import com.demo.kafka.examples.types.OrderAggregate;

class CustomOrderSerdes extends Serdes {

    static final class OrderSerde extends WrapperSerde<Order> {
    	OrderSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<Order> Order() {
    	OrderSerde serde = new OrderSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", Order.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class OrderAggSerde extends WrapperSerde<OrderAggregate> {
    	OrderAggSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<OrderAggregate> OrderAggregate() {
    	OrderAggSerde serde = new OrderAggSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", OrderAggregate.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
