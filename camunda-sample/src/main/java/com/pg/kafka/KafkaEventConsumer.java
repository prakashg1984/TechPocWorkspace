package com.pg.kafka;

import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pg.bo.OrderEventRequest;

/**
 * @author Prakash_G01
 *
 */
@Component
@EnableKafka
public class KafkaEventConsumer {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@KafkaListener(topics = "${test.event.topic}", containerFactory = "kafkaListenerContainerFactory")
	public void receiveOrderEvent(OrderEventRequest eventRequest) {
		String customerOrderNr = ((Map<String,Object>) eventRequest.any().get("event")).get("customerOrderNumber").toString();
		
		ProcessInstanceWithVariables pVariablesInReturn = runtimeService.createProcessInstanceByKey("OrderProcessEvent")
				.setVariable("eventRequest", eventRequest)
				.businessKey(customerOrderNr)
				.executeWithVariablesInReturn() ;
	}
	
	
	@KafkaListener(topics = "${test.fallout.topic}", containerFactory = "taskFafkaListenerContainerFactory")
	public void receiveFalloutEvent(OrderEventRequest eventRequest) {
		String taskId = ((Map<String,Object>) eventRequest.any().get("event")).get("camundaTaskId").toString();
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.complete(task.getId());
	}
}
