package com.demo.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.demo.kafka.model.User;


@Configuration
@EnableKafka
public class KafkaEventConsumerConfig {

	@Value("${kafka.bootstrap.servers:localhost:9092}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
	    JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);


		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, createDeserializer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "Event_Consumer");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		//props.put(SaslConfigs.SASL_JAAS_CONFIG,"org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"admin123\";");
		//props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		//props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
		return props;
	}
	@Bean
	public ConsumerFactory<String, User> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), createDeserializer());
	}
	@Bean(name = "kafkaListenerContainerFactory")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, User>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConcurrency(10);
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	@Bean
	public KafkaEventConsumer consumer() {
		return new KafkaEventConsumer();
	}
	
	private JsonDeserializer createDeserializer() {
		JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);
	    
	    return deserializer;
	}
}
