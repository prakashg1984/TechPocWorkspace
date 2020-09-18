
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaNewProducerSASL {

	public static void main(String[] args) {

		String data = "test";

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:2181");

		props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put("acks", "all");

		// configure the following three settings for SSL Encryption
		// props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
		// "props/client.truststore.jks");
		// props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "admin");
		props.put("ssl.truststore.type", "jks");

		// configure the following three settings for SSL Authentication
		/*
		 * props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
		 * "props/server.keystore.jks");
		 * props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "kafka123");
		 * props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "kafka123");
		 */

		// configure the below for SASL authentication
		String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
		String jaasConfig = String.format(jaasTemplate, "admin", "admin");
		props.put("security.protocol", "SASL_PLAINTEXT");
		props.put("sasl.mechanism", "PLAIN");
		props.put("sasl.jaas.config", jaasConfig);

		Producer<String, String> producer = new KafkaProducer<>(props);
		try {
			List<Header> headers = new ArrayList<>();
			headers.add(new RecordHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka".getBytes()));
			System.out.println("Before send");
			for (int i = 40; i < 60; i++) {
				final ProducerRecord<String, String> record = new ProducerRecord<String, String>("TOPIC_NAME", 4,
						"TEST_KEY" + i, data, headers);
				producer.send(record);
			}
			System.out.println("After send");

		} finally {
			producer.close();
		}
	}

}
