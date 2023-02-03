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

import com.demo.kafka.examples.types.DepartmentAggregate;
import com.demo.kafka.examples.types.Employee;

public class KStreamAggDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
		//props.put(StreamsConfig.STATE_DIR_CONFIG, TestUtils.tempDirectory().getAbsolutePath());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Employee> KS0 = streamsBuilder.stream(AppConfigs.topicName,
            Consumed.with(AppSerdes.String(), AppSerdes.Employee()));

        KGroupedStream<String, Employee> KGS1 = KS0.groupBy(
            (k, v) -> v.getDepartment(),
            Serialized.with(AppSerdes.String(),
                AppSerdes.Employee()));

        KTable<String, DepartmentAggregate> KT2 = KGS1.aggregate(
            //Initializer
            () -> new DepartmentAggregate()
                .withEmployeeCount(0)
                .withTotalSalary(0)
                .withAvgSalary(0D),
            //Aggregator
            (k, v, aggV) -> new DepartmentAggregate()
                .withEmployeeCount(aggV.getEmployeeCount() + 1)
                .withTotalSalary(aggV.getTotalSalary() + v.getSalary())
                .withAvgSalary((aggV.getTotalSalary() + v.getSalary()) / (aggV.getEmployeeCount() + 1D)),
            //Serializer
            Materialized.<String, DepartmentAggregate, KeyValueStore<Bytes, byte[]>>as("agg-store")
                .withKeySerde(AppSerdes.String())
                .withValueSerde(AppSerdes.DepartmentAggregate())
        );

        KT2.toStream().foreach(
            (k, v) -> System.out.println("Key = " + k + " Value = " + v.toString()));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
