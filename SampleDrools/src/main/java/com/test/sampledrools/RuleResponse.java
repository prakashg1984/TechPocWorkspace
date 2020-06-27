package com.test.sampledrools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuleResponse {

	private List<Map<String,Object>> rulesList;
	
	public RuleResponse() {
        rulesList = new ArrayList<Map<String,Object>>();
    }
	
	public List<Map<String,Object>> getRulesList() {
		return rulesList;
	}
	public void setRulesList(List<Map<String,Object>> rulesList) {
		this.rulesList = rulesList;
	}
	
	public void addRuleResponse(Map<String,Object> response) {
        rulesList.add(response);
    }

}
