package com.pg.delegates

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("csiDelegate")
class CsiDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		def eventRequest = execution.getVariable("eventRequest") ;
		def orderDataObject =  eventRequest.data.data.order;
		def eventDataObject =  eventRequest.data.event;
		UUID uuid = UUID.randomUUID();
		
		def groups = orderDataObject.productGroups.group;
		
		Map<Object,Object> losgStatus = new HashMap<Object,Object>();
		
		if(execution.getCurrentActivityName().contains("Account")) {
			losgStatus.put("status", "SYS_PROCESSING");
			losgStatus.put("subStatus", "ACCOUNT_CREATED_PASS");
			
			def accounts = orderDataObject.accounts.account;
			
			for(def account : accounts){
				account.put("billingAccountNumber", "123456");
			}
			eventDataObject.eventType = "AccountCreated"
		}
		if(execution.getCurrentActivityName().contains("Subscriber")) {
			losgStatus.put("status", "SYS_PROCESSING");
			losgStatus.put("subStatus", "RESERVE_SUBSCRIBER_NUMBER_PASS");
						
			for(Map<String,Object> group : groups){
				def wirelessLosgCharacteristics = group.characteristics.losgCharacteristics.wirelessLOSCharacteristics;
				wirelessLosgCharacteristics.put("mobileNumber", "6789012345");
			}
			eventDataObject.eventType = "SubscriberCreated"
		}
		if(execution.getCurrentActivityName().contains("Equipment")) {
			losgStatus.put("status", "SUBMITTED");
			losgStatus.put("subStatus", "ORDER_EQUIPMENT_PASS");
			eventDataObject.eventType = "EquipmentOrdered"
		}
		if(execution.getCurrentActivityName().contains("UpdateOrder")) {
			eventDataObject.eventType = "UpdateOrder"
		}
		
		for(def group : groups){
			def losgCharacteristics = group.characteristics.losgCharacteristics;
			if(!losgStatus.isEmpty()) {
				losgCharacteristics.put("losgStatus", losgStatus);
			}
		}
		
		eventDataObject.eventid = uuid.toString();
		execution.setVariable("eventRequest", eventRequest);
		println "After CsiDelegate : "+eventRequest.data.toString()
	}
}
