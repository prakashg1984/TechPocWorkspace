package com.att.pg;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerBean {
	
	
	@Autowired
	KafkaListenerContainerFactory factory;
	
	@Value("${consumer.topic}")
	private String topic;
	
	@Value("${kafka.consumer.group.id}")
	private String consumerGroup;
	
	/**
	 * This method can be used if we need to switch on/off the subscriber based on flag
	 */
	public void runConsumer() {
		ConcurrentKafkaListenerContainerFactory concFactory = (ConcurrentKafkaListenerContainerFactory) factory;
		final Consumer<String, String> consumer = concFactory.getConsumerFactory().createConsumer();
		System.out.println("Subscribing to "+topic);
		consumer.subscribe(Collections.singletonList(topic));
		
		List<PartitionInfo> assignedPartitionsInfos = consumer.partitionsFor(topic);

		Set<TopicPartition> partitionList = new HashSet<TopicPartition>();
		for(PartitionInfo assignedPartitionsInfo : assignedPartitionsInfos) {
			partitionList.add(new TopicPartition(assignedPartitionsInfo.topic(), assignedPartitionsInfo.partition()));
		}
		
		
		while (true) {
			Properties props = new Properties();
			boolean shouldRun = true;
			long waitTime = 10000;
			try {
				InputStream input = new FileInputStream("C://Installables//config.properties");
				props.load(input);
				shouldRun = Boolean.valueOf(props.getProperty("shouldRun"));
				waitTime = Long.valueOf(props.getProperty("waitTime"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (shouldRun) {
				if (consumer.paused() != null && consumer.paused().size() > 0) {
					consumer.resume(consumer.paused());
				}
				ConsumerRecords<String, String> records = consumer.poll(10000);

				for (ConsumerRecord<String, String> record : records) {
					System.out.println("Value For Normal Poll " + record.value());
				}

				//consumer.commitAsync();

			} else {
				consumer.pause(partitionList);
				ConsumerRecords<String, String> records = consumer.poll(0);
				System.out.println("Record Size : " + records.count());
				System.out.println("partitionList " + partitionList.toString());
				System.out.println("Paused : " + consumer.paused());
			}
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}