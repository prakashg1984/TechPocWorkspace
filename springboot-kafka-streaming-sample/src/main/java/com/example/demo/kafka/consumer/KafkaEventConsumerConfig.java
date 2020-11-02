package com.example.demo.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.LongDeserializer;
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

/**
 * @author Prakash_G01
 *
 */
@Configuration
@EnableKafka
public class KafkaEventConsumerConfig {

	@Value("${kafka.bootstrap.servers:localhost:9092}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "Event_Consumer");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		// props.put(SaslConfigs.SASL_JAAS_CONFIG,
		// "org.apache.kafka.common.security.plain.PlainLoginModule required
		// username=\"admin\" password=\"12345\";");
		// props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		// props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new StringDeserializer());
	}

	@Bean(name = "kafkaListenerContainerFactory")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConcurrency(10);
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public KafkaEventConsumer consumer() {
		return new KafkaEventConsumer();
	}

	@Bean
	public ConsumerFactory<String, Long> consumerFactoryLong() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new LongDeserializer());
	}

	@Bean(name = "kafkaListenerContainerFactoryLong")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Long>> kafkaListenerContainerFactoryLong() {
		ConcurrentKafkaListenerContainerFactory<String, Long> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConcurrency(10);
		factory.setConsumerFactory(consumerFactoryLong());
		return factory;
	}

}
