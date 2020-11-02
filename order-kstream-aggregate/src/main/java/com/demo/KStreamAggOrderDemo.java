package com.demo;

import java.util.Properties;

import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.state.KeyValueStore;

import com.demo.kafka.examples.types.Order;
import com.demo.kafka.examples.types.OrderAggregate;


public class KStreamAggOrderDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
		//props.put(StreamsConfig.STATE_DIR_CONFIG, TestUtils.tempDirectory().getAbsolutePath());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Order> orderStream = streamsBuilder.stream(AppConfigs.topicName,
            Consumed.with(CustomOrderSerdes.String(), CustomOrderSerdes.Order()));

        KGroupedStream<String, Order> productGroupStream = orderStream.groupBy(
            (k, v) -> v.getAny().get("product").toString(),
            Serialized.with(CustomOrderSerdes.String(),
            		CustomOrderSerdes.Order()));

        KTable<String, OrderAggregate> productTable = productGroupStream.aggregate(
            //Initializer
            () -> new OrderAggregate()
                .withCount(0),
            //Aggregator
            (k, v, aggV) -> new OrderAggregate()
                .withCount(aggV.getCount() + 1)
                ,
            //Serializer
            Materialized.<String, OrderAggregate, KeyValueStore<Bytes, byte[]>>as("product-store")
                .withKeySerde(CustomOrderSerdes.String())
                .withValueSerde(CustomOrderSerdes.OrderAggregate())
        );

        
        KGroupedStream<String, Order> losgGroupStream = orderStream.groupBy(
            (k, v) -> v.getAny().get("losgType").toString(),
            Serialized.with(CustomOrderSerdes.String(),
            		CustomOrderSerdes.Order()));

        KTable<String, OrderAggregate> losgTable = losgGroupStream.aggregate(
            //Initializer
            () -> new OrderAggregate()
                .withCount(0),
            //Aggregator
            (k, v, aggV) -> new OrderAggregate()
                .withCount(aggV.getCount() + 1)
                ,
            //Serializer
            Materialized.<String, OrderAggregate, KeyValueStore<Bytes, byte[]>>as("losg-store")
                .withKeySerde(CustomOrderSerdes.String())
                .withValueSerde(CustomOrderSerdes.OrderAggregate())
        );
        
        productTable.toStream().foreach(
                (k, v) -> System.out.println("Key = " + k + " Value = " + v.toString()));
        
        losgTable.toStream().foreach(
            (k, v) -> System.out.println("Key = " + k + " Value = " + v.toString()));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
