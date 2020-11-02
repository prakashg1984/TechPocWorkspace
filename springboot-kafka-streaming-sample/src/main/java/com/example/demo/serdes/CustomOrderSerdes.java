package com.example.demo.serdes;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.example.demo.type.Order;
import com.example.demo.type.OrderAggregate;


public class CustomOrderSerdes extends Serdes {

	public static final class OrderSerde extends WrapperSerde<Order> {
    	OrderSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

	public static Serde<Order> Order() {
    	OrderSerde serde = new OrderSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", Order.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

	public static final class OrderAggSerde extends WrapperSerde<OrderAggregate> {
    	OrderAggSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

	public static Serde<OrderAggregate> OrderAggregate() {
    	OrderAggSerde serde = new OrderAggSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", OrderAggregate.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
