package hello;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleKafkaConsumer {

    static Logger logger = LoggerFactory.getLogger(SampleKafkaConsumer.class);

	public static void main(String[] args) {
		
		KafkaConsumer<String, String> kafkaConsumer;
		String topicName="TESTTOPIC"; String userName = "admin"; String password = "admin123";
		
		Properties prop = new Properties();
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumer");
		prop.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");
		
		prop.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		prop.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
		prop.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" 
				+ userName + "\" password=\"" + password + "\";");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		kafkaConsumer = new KafkaConsumer<String, String>(prop);
		kafkaConsumer.subscribe(Arrays.asList(topicName));

		try {
			while (true) {
				ConsumerRecords<String, String> recs = kafkaConsumer.poll(100);
				for (ConsumerRecord<String, String> rec : recs)
					logger.info(rec.value());
			}
		}catch(WakeupException ex){
			ex.printStackTrace();
		}finally{
			kafkaConsumer.close();
		}
	}
}
