package com.pg.processor;

import org.apache.camel.Exchange;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageProcessor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	 
	public void startInstance(Exchange exchange){
		ProcessInstanceWithVariables pVariablesInReturn = runtimeService.createProcessInstanceByKey("SamplePGEvent")
				.setVariable("message", exchange.getIn().getBody())
				.businessKey(String.valueOf(exchange.getIn().getBody().hashCode()))
				.executeWithVariablesInReturn() ;
		
		String piid = pVariablesInReturn.getProcessInstanceId();
		
		logger.info("Process Started "+piid);
	}
	
	
	public void endInstance(Exchange exchange){
		Task task = taskService.createTaskQuery().processInstanceId(exchange.getIn().getBody().toString()).singleResult();
	    taskService.complete(task.getId());
		
	    logger.info("Task completed for "+exchange.getIn().getBody());
	}
}
