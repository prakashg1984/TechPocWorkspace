package com.pg.delegates

import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

@Component("commandDelegate")
class CommandDelegate implements JavaDelegate {

	@Produce(uri = "direct:createEvent")
    private ProducerTemplate template;

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		println "Inside Command Delegate"
		def eventRequest = execution.getVariable("eventRequest") ;
		def eventDataObject =  eventRequest.data.event;
		UUID uuid = UUID.randomUUID();
				
		if(execution.getCurrentActivityName().contains("UpdateOrder")) {
			eventDataObject.eventType = "UpdateOrder"
		}
		
		eventDataObject.eventid = uuid.toString();
		execution.setVariable("eventRequest", eventRequest);
		Map<String, Object> exchangeVariables = new HashMap<String, Object>();
		exchangeVariables.put("eventRequest", eventRequest);
		template.asyncSendBody(template.getDefaultEndpoint(), exchangeVariables);
	}
}
