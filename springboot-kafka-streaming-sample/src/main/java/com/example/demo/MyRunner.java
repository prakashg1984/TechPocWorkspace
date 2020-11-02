package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.test.TestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.extract.Helper;
import com.example.demo.serdes.CustomOrderSerdes;
import com.example.demo.service.KafkaProducerService;
import com.example.demo.type.Order;
import com.example.demo.type.OrderAggregate;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private KafkaProducerService kafkaProducerService;
    
    @Autowired 
    Helper helper;

	@Override
	public void run(String... args) throws Exception {
		
		//startKStream();
		startOrderKStream();
		for(int i=0;i<20;i++) {
			kafkaProducerService.sendMessage("Sending New Message "+i);
		}
		logger.info("After Send");

	}
	
	public void startKStream() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
		props.put(StreamsConfig.STATE_DIR_CONFIG, TestUtils.tempDirectory().getAbsolutePath());

		StreamsBuilder builder = new StreamsBuilder();
		final Serde<String> stringSerde = Serdes.String();
		final Serde<Long> longSerde = Serdes.Long();


		//KStream<String, String> textLines = builder.stream("SAMPLE_DEV_TOPIC2");
		KStream<String, String> textLines = builder.stream("SAMPLE_DEV_TOPIC2",
			    Consumed.with(stringSerde, stringSerde));
		
		
		/*KTable<String, Long> wordCounts = textLines
				.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("W+")))
				.groupBy((key, word) -> word)
				.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
				*/
		
		KTable<String, Long> wordCounts = textLines
				.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("W+")))
				.groupBy((key, word) -> word)
				.count(Materialized.as("counts-store"));
		
		//System.out.println("wordCounts "+wordCounts.toStream().toTable().toStream().toString());
		
		
		wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();

	}
	
	@SuppressWarnings("deprecation")
	public void startOrderKStream() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
		props.put(StreamsConfig.STATE_DIR_CONFIG, TestUtils.tempDirectory().getAbsolutePath());
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Order> orderStream = streamsBuilder.stream("ordertopic",
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
            (k, v) -> helper.fetchLosgTypes(v),
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
        
        String previousDate = "";
        while(true) {
        	String currDate = getCurrDate();
        	if(!currDate.equalsIgnoreCase(previousDate)) {
        		System.out.println("previousDate "+previousDate + " : currDate "+currDate);
        		previousDate = currDate;
        		streams.close(10, TimeUnit.SECONDS);
        		streams.cleanUp();
        		streams = new KafkaStreams(streamsBuilder.build(), props);
        		streams.start();
        	}
        }
	}
	
	private String getLosgType(Order order) {
		
		
		return null;
	}
	
	private String getCurrDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
		   LocalDateTime now = LocalDateTime.now();  
		  // System.out.println(dtf.format(now));
		   
		   return dtf.format(now);
	}
	
}
