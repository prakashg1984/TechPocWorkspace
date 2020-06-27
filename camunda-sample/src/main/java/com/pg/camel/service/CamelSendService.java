package com.pg.camel.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("camel")
public class CamelSendService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CamelContext camelContext;
	
	public Object sendTo(String endpointUri, String processVariables) throws Exception {
		
		logger.info("processVariables "+ processVariables);
		Collection<String> vars;
		ActivityExecution execution = (ActivityExecution) Context.getBpmnExecutionContext().getExecution();
		Map<String, Object> variablesToSend = new HashMap<String, Object>();
		ArrayList<String> headers = new ArrayList<String>();
		
		if (processVariables == null) {
			execution = Context.getBpmnExecutionContext().getExecution();
			vars = execution.getVariableNames();
		} else if ("".equals(processVariables)) {
			vars = Collections.emptyList();
		} else {
			vars = Arrays.asList(processVariables.split("\\s*,\\s*"));
		}
		logger.info("execution "+execution.getVariables());
		logger.info("execution.getBusinessKey "+execution.getBusinessKey());

		for (String var : vars) {
			if (execution.hasVariable(var)) {
				variablesToSend.put(var, execution.getVariable(var));
			} else {
				headers.add(var);
			}
		}
		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		String businessKey = execution.getBusinessKey();
		Exchange exchange = new DefaultExchange(camelContext);
		
		exchange.getIn().setBody(variablesToSend);
		
		int i = 1;
		for (String header : headers) {
			exchange.getIn().getHeaders().put("param" + i, header);
			i++;
		}

		exchange.setPattern(ExchangePattern.InOut);
		Exchange send = producerTemplate.send(endpointUri, exchange);
		
		Object r = send.getOut().getBody();
		if (r instanceof String) {
			r = new StringBuilder().append(r);
		}
		logger.info("Output Result "+r);
		return r;
	}
}
