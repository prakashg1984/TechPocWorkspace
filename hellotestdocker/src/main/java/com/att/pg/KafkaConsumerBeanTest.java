package com.att.pg;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.att.oce.consumer.OCEConsumerRebalancerListener;
import com.att.oce.consumer.OCEKafkaMessageListenerContainer;
import com.att.oce.util.OffsetManager;


@Component
public class KafkaConsumerBeanTest {
	
	Logger logger = LoggerFactory.getLogger(KafkaConsumerBeanTest.class);

	@Autowired
	KafkaListenerContainerFactory factory;
	
	@Value("${consumer.topic}")
	private String topic;
	
	@Value("${kafka.consumer.group.id}")
	private String consumerGroup;
	
	@Autowired
	OffsetManager offsetManager;
	
	@Autowired
	OCEConsumerRebalancerListener oCEConsumerRebalancerListener;
	
	@Autowired
	OCEKafkaMessageListenerContainer kafkaMessageListenerContainer;
	
	/**
	 * This method can be used if we need to switch on/off the subscriber based on flag
	 */
	@Async
	public void runConsumer() {
		ConcurrentKafkaListenerContainerFactory concFactory = (ConcurrentKafkaListenerContainerFactory) factory;
		final Consumer<String, String> consumer = concFactory.getConsumerFactory().createConsumer();
		
		//Sets the AckTime and Count value for Listener commit
		ContainerProperties containerProperties = concFactory.getContainerProperties();
		containerProperties.setAckMode(AckMode.COUNT_TIME);
		containerProperties.setAckTime(5000);
		containerProperties.setAckCount(20);
		
		oCEConsumerRebalancerListener.setOCEConsumerRebalancerListener(consumer,consumerGroup);
		kafkaMessageListenerContainer.setContainerProperties(containerProperties, consumer);
		
		consumer.subscribe(Collections.singletonList(topic),oCEConsumerRebalancerListener);
		
		List<PartitionInfo> assignedPartitionsInfos = consumer.partitionsFor(topic);

		Set<TopicPartition> partitionList = new HashSet<TopicPartition>();
		for(PartitionInfo assignedPartitionsInfo : assignedPartitionsInfos) {
			partitionList.add(new TopicPartition(assignedPartitionsInfo.topic(), assignedPartitionsInfo.partition()));
		}
		
		long startTime = System.currentTimeMillis();
		
		while (true) {
			long nowTime = System.currentTimeMillis();
			long timeElapsed = (nowTime - startTime)/1000;
			//System.out.println("TimeElapsed in seconds: " + timeElapsed);
			logger.info("TimeElapsed in seconds: " + timeElapsed);

			//Properties props = new Properties();
			boolean shouldRun = true;
			if(timeElapsed > 100 && timeElapsed < 200) {
				shouldRun = false;
			}else if (timeElapsed >= 200){
				shouldRun = true;
				startTime = System.currentTimeMillis();
			}
			long waitTime = 5000;

			if (shouldRun) {
				if (consumer.paused() != null && consumer.paused().size() > 0) {
					consumer.resume(consumer.paused());
				}
				ConsumerRecords<String, String> records = consumer.poll(5000);
				logger.info("Record Size in NORMAL consumer Poll: " + records.count());
				for (ConsumerRecord<String, String> record : records) {
					if(record.offset() > offsetManager.readOffsetFromExternalStore(consumerGroup, record.topic(), record.partition())) {
						logger.info("Value For Normal Poll " + record.value());
						logger.info("Offset " + record.offset());
						kafkaMessageListenerContainer.processCommits(record,consumerGroup);
						offsetManager.saveOffsetInExternalStore(consumerGroup, record.topic(), record.partition(), record.offset());
						//consumer.commitAsync();
					}
				}
			} else {
				consumer.pause(partitionList);
				ConsumerRecords<String, String> records = consumer.poll(0);
				logger.info("Record Size in ZERO consumer Poll : " + records.count());
				if(records.count() > 0) {
					logger.error("Record Size in ZERO consumer Poll : " + records.count());
				}
			}
			if (timeElapsed >= 240){
				logger.info("---------------------------------------------");
				//System.out.println("---------------------------------------------");
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
