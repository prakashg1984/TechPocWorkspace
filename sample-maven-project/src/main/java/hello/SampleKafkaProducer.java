package hello;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleKafkaProducer {

    static Logger logger = LoggerFactory.getLogger(SampleKafkaProducer.class);

	public static void main(String[] args) {
		Properties props = new Properties();

		String userName = "admin";
		String password = "admin123";
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 33554432);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
		props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" 
				+ userName + "\" password=\"" + password + "\";");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		logger.info("Before send");
		for (int i = 0; i < 10; i++) {
			try {
				ProducerRecord<String,String> rec = new ProducerRecord<String,String>("TESTTOPIC",""+i,"NEW_MSG-"+i);
				producer.send(rec);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Message Published");
		producer.close();
	}
}
