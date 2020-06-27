
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaNewConsumerSASL {

	public static void main(String[] args) {

		Properties props = new Properties();

		String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
		String jaasConfig = String.format(jaasTemplate, "admin", "admin");
		props.put("security.protocol", "SASL_SSL");
		props.put("sasl.mechanism", "PLAIN");
		props.put("sasl.jaas.config", jaasConfig);

		props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "props/client.truststore.jks");
		props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "admin");

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:2181");

		props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group-6");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10000);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");

		final Consumer<String, String> consumer = new KafkaConsumer<>(props);
		List<String> topicName = new ArrayList<String>();
		topicName.add("TOPIC_NAME");

		List<TopicPartition> topicsPartions = new ArrayList<TopicPartition>();
		TopicPartition tp = new TopicPartition("TOPIC_NAME", 4);
		topicsPartions.add(tp);
		consumer.assign(topicsPartions);
		// consumer.subscribe(topicName);

		System.out.println("Before poll");
		ConsumerRecords<String, String> records = null;
		try {
			records = consumer.poll(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After poll " + records.count());
		try {
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("record : " + record.key());
				System.out.println("value : " + record.value());

				consumer.commitSync();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}

}
