package com.att.pg;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;


@Configuration
public class KafkaConsumerConfig {

	@Value("${kafka.consumer.bootstrap.servers}")
	private String bootstrapServers;
	
	@Value("${kafka.consumer.group.id}")
	private String consumer;
	
	@Bean
    public Map<String, Object> consumerConfigs() {/*
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Sample_Consumer2");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig. FETCH_MAX_WAIT_MS_CONFIG, "10000");
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 2000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 20000);
        props.put("max.poll.interval.ms", Long.MAX_VALUE);

        return props;
    */

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumer);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        //props.put(ConsumerConfig. FETCH_MAX_WAIT_MS_CONFIG, "10000");
        
        props.put("enable.auto.commit", "false");
        props.put("heartbeat.interval.ms", "2000");
        props.put("session.timeout.ms", "10000");
        props.put("max.partition.fetch.bytes", "10000");

        return props;
    
	}
	
	@Bean
	  public ConsumerFactory<String, String> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
	        new StringDeserializer());
	  }
	
  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConcurrency(10);
      factory.setConsumerFactory(consumerFactory());
      factory.getContainerProperties().setPollTimeout(30000);
      
      return factory;
  }

}
