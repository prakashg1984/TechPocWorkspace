package com.pg.service

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.camunda.bpm.engine.delegate.DelegateTask
import org.camunda.bpm.engine.delegate.TaskListener
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Component

@Component("myTaskAssignmentHandler")
class MyTaskAssignmentHandler implements TaskListener {
	
	@Override
	public void notify(DelegateTask delegateTask) {
		def orderEventRequest = delegateTask.getVariable("eventRequest");
		println "orderEventRequest : "+orderEventRequest.data.toString()
		def event = orderEventRequest.data.event;
		
		String customerOrderNr = event.customerOrderNumber;
		event.camundaTaskId =  delegateTask.getId();
		event.eventType = "CreateFallout"
		event.eventid = UUID.randomUUID().toString();
		
		Producer<String, Object> producer = createProducer();
		println "event : "+event.toString()
		ProducerRecord<String, Object> record = new ProducerRecord<>("ORDER_EVENT_TOPIC", customerOrderNr, orderEventRequest);
		producer.send(record);
	}
	
	
	
	public static Producer<String, Object> createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, "0");
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new KafkaProducer<>(props);
	}
}
