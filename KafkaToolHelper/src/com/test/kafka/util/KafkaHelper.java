/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.kafka.util;

import com.test.kafka.bo.ConsumerInfo;
import com.test.kafka.bo.TopicInfo;
import com.test.kafka.form.KafkaTool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.DeleteRecordsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsOptions;
import org.apache.kafka.clients.admin.ListConsumerGroupsOptions;
import org.apache.kafka.clients.admin.RecordsToDelete;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 *
 * @author PGPS
 */
public class KafkaHelper {
    private final static Logger LOGGER = Logger.getLogger(KafkaHelper.class.getName());
    Map<String,KafkaConsumer> kafkaConsumerMap = new HashMap<>();
    public Collection showConsumers(Map<String, Object> props){
        LOGGER.log(Level.INFO, "Trying to get showConsumers : {0}", props.toString());
        try {
                AdminClient adminClient = KafkaAdminClient.create(props);
                LOGGER.log(Level.INFO, "AdminClient Connected : {0}", adminClient.describeCluster());
		Collection<ConsumerGroupListing> consumers = adminClient
                                    .listConsumerGroups(new ListConsumerGroupsOptions()).all()
                                    .get(30, TimeUnit.SECONDS);	
                return consumers;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    public List showTopics(Map<String, Object> props){
        LOGGER.log(Level.INFO, "Trying to get showConsumers : {0}", props.toString());
        List<String> topicList = new ArrayList<>();
        try {
                AdminClient adminClient = KafkaAdminClient.create(props);
                LOGGER.log(Level.INFO, "AdminClient Connected : {0}", adminClient.describeCluster());
		
                Set<String> topics;
                topics = adminClient.listTopics().names().get(30, TimeUnit.SECONDS);
                Map<String, TopicDescription> topicDetails = adminClient.describeTopics(topics).all().get(30,
						TimeUnit.SECONDS);
                topicDetails.values().stream().forEach((td) -> {
                    topicList.add(td.name());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return topicList;
    }
    
    public List<ConsumerInfo> getLag(String consumerName, Map<String, Object> props){
        List<ConsumerInfo> consumerInfoList = new ArrayList<>();
        
        try{
            List<String> groupIds = new ArrayList<>();
            groupIds.add(consumerName);
            final AdminClient adminClient = KafkaAdminClient.create(props);
            Map<String, ConsumerGroupDescription> groupDesc = adminClient.
                                               describeConsumerGroups(groupIds).all().get();

            ConsumerGroupDescription cgDescr = groupDesc.get(consumerName);
            
            Stream<Set<TopicPartition>> map = cgDescr.members().stream().
                    map(s -> s.assignment().topicPartitions());
            
            Map<TopicPartition, OffsetAndMetadata> offsets = adminClient
							.listConsumerGroupOffsets(consumerName, new ListConsumerGroupOffsetsOptions())
							.partitionsToOffsetAndMetadata().get(30, TimeUnit.SECONDS);
            
            map.forEach((cnsmr) -> {
                Set<TopicPartition> tpList = cnsmr;
                tpList.stream().forEach((tp) -> { 
                    long committedOffset = offsets.get(tp).offset();
                    long finalOffset = getLogEndOffset(tp,props);
                    long lag = finalOffset - committedOffset;
                    ConsumerInfo consumerInfo = new ConsumerInfo();
                    System.out.println("Topic Partition : "+tp.topic() + " : "+ tp.partition() + " : "+lag);
                    
                    consumerInfo.setTopicPartition(tp.topic() + " : "+ tp.partition());
                    consumerInfo.setFinalOffset(finalOffset);
                    consumerInfo.setLag(lag);
                    consumerInfo.setCommittedOffset(committedOffset);
                    
                    if(lag > 0){
                        consumerInfo.setStatus("Lag");
                    }else{
                        consumerInfo.setStatus("No Lag");
                    }
                    consumerInfoList.add(consumerInfo);
                });
            });
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return consumerInfoList;
    }
    
    
    public List<ConsumerInfo> findAllLag(Map<String, Object> props){
        List<ConsumerInfo> consumerInfoList = new ArrayList<>();
        
        try{
            final AdminClient adminClient = KafkaAdminClient.create(props);
            
            Collection<ConsumerGroupListing> consumers = adminClient
						.listConsumerGroups(new ListConsumerGroupsOptions()).all().get(30, TimeUnit.SECONDS);
            
            for (ConsumerGroupListing cg : consumers) {
                Map<TopicPartition, OffsetAndMetadata> offsets = adminClient
							.listConsumerGroupOffsets(cg.groupId(), new ListConsumerGroupOffsetsOptions())
							.partitionsToOffsetAndMetadata().get(30, TimeUnit.SECONDS);
                
                for (TopicPartition tp : offsets.keySet()) {
                    
                    long committedOffset = offsets.get(tp).offset();
                    long finalOffset = getLogEndOffset(tp,props);
                    long lag = finalOffset - committedOffset;
                    System.out.println("Topic Partition : "+tp.topic() + " : "+ tp.partition() + " : "+lag);
                    if(lag > 1){
                        ConsumerInfo consumerInfo = new ConsumerInfo();
                        
                        consumerInfo.setConsumerGroupId(cg.groupId());
                        consumerInfo.setTopicPartition(tp.topic() + " : "+ tp.partition());
                        consumerInfo.setFinalOffset(finalOffset);
                        consumerInfo.setLag(lag);
                        consumerInfo.setCommittedOffset(committedOffset);
                        consumerInfo.setStatus("Lag");
                        
                        consumerInfoList.add(consumerInfo);
                    }
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return consumerInfoList;
    }
    
    public List<TopicInfo> getTopicInfo(String topicName, Map<String, Object> props){
        List<TopicInfo> topicInfoDetails = new ArrayList<>();
        try{
            final AdminClient adminClient = KafkaAdminClient.create(props);
            List<String> topicsList = new ArrayList<String>();
            topicsList.add(topicName);
            Map<String, TopicDescription> topicDetails = adminClient.describeTopics(topicsList).all().get(30,
						TimeUnit.SECONDS);
            
            for (TopicDescription td : topicDetails.values()) {
                    for (TopicPartitionInfo tpi : td.partitions()) {
                        String topicPartitionName = td.name() + "-" + tpi.partition();
                        TopicInfo topicInfo = new TopicInfo();
                        
                        topicInfo.setTopicPartition(topicPartitionName);
                        topicInfo.setIsrCount(tpi.isr().size());
                        topicInfo.setReplicaCount(tpi.replicas().size());
                        topicInfo.setLeaderHost(tpi.leader().host());
                        
                        topicInfoDetails.add(topicInfo);
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return topicInfoDetails;
    }
    
    
    public List<TopicInfo> getAllTopicInfo(Map<String, Object> props){
        List<TopicInfo> topicInfoDetails = new ArrayList<>();
        try{
            final AdminClient adminClient = KafkaAdminClient.create(props);
            Set<String> topics = adminClient.listTopics().names().get(30, TimeUnit.SECONDS);
            Map<String, TopicDescription> topicDetails = adminClient.describeTopics(topics).all().get(30,
                            TimeUnit.SECONDS);
            
            for (TopicDescription td : topicDetails.values()) {
                    for (TopicPartitionInfo tpi : td.partitions()) {
                        String topicPartitionName = td.name() + "-" + tpi.partition();
                        
                        if (null == tpi.leader() || tpi.leader().host() == null) {
                            TopicInfo topicInfo = new TopicInfo();
                        
                            topicInfo.setTopicPartition(topicPartitionName);
                            topicInfo.setIsrCount(tpi.isr().size());
                            topicInfo.setReplicaCount(tpi.replicas().size());
                            topicInfo.setLeaderHost(tpi.leader().host());
                            topicInfo.setStatus("NO_LEADER");
                            topicInfoDetails.add(topicInfo);
                        }
                        
                        if(tpi.isr().size() < tpi.replicas().size() || tpi.isr().size() < 3 || tpi.replicas().size() < 3){
                            TopicInfo topicInfo = new TopicInfo();
                        
                            topicInfo.setTopicPartition(topicPartitionName);
                            topicInfo.setIsrCount(tpi.isr().size());
                            topicInfo.setReplicaCount(tpi.replicas().size());
                            topicInfo.setLeaderHost(tpi.leader().host());
                            topicInfo.setStatus("ISR_REPLICA_ISSUE");
                            topicInfoDetails.add(topicInfo);
                        }
                       
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return topicInfoDetails;
    }
    
    public long getLogEndOffset(TopicPartition tp, Map<String, Object> props) {
                KafkaConsumer consumer;
		if (!kafkaConsumerMap.containsKey(props.get("bootstrap.servers"))) {
			consumer = createNewConsumer(props);
                        kafkaConsumerMap.put(props.get("bootstrap.servers").toString(), consumer);
		}else{
                    consumer = kafkaConsumerMap.get(props.get("bootstrap.servers").toString());
                }
		Collections.singletonList(tp);
		consumer.assign(Collections.singletonList(tp));
		consumer.seekToEnd(Collections.singletonList(tp));
		return consumer.position(tp);
	}
    
    private KafkaConsumer<String, String> createNewConsumer(Map<String, Object> props) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.get("bootstrap.servers"));
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "kt1");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		return new KafkaConsumer(properties);
	}
    
    
    public boolean deleteMessages(Map<String, Object> props , String topicName, String partition, String offset){
        try{
            final AdminClient adminClient = KafkaAdminClient.create(props);
            TopicPartition topicPartition = new TopicPartition(topicName, Integer.valueOf(partition));
            Map<TopicPartition, RecordsToDelete> deleteMap = new HashMap<>();
            deleteMap.put(topicPartition, RecordsToDelete.beforeOffset(Integer.valueOf(offset)));
            
            DeleteRecordsResult result = adminClient.deleteRecords(deleteMap);
            
            System.out.println("com.test.kafka.util.KafkaHelper.deleteMessages() "+result.lowWatermarks().toString());
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean sendMessages(Map<String, Object> inputProps, String topicName, String message, String key){
         
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, inputProps.get("bootstrap.servers").toString());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "ATGKafkaProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String, String> producer = new KafkaProducer<>(props);
        
        try {
            final ProducerRecord<String, String> record = new ProducerRecord<String, String>(
						topicName, null, key , message);
            producer.send(record);
            
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            producer.close();
        }
        return false;
     }
}
