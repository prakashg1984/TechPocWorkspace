package com.test.model;

import java.util.List;

public class RuleDetail {


	public String ruleId;
	public String ruleName;
	public String ruleType;
	public boolean isActive;
	public List<RuleCondition> ruleConditionList;
	public RuleAction ruleAction;
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public List<RuleCondition> getRuleConditionList() {
		return ruleConditionList;
	}
	public void setRuleConditionList(List<RuleCondition> ruleConditionList) {
		this.ruleConditionList = ruleConditionList;
	}
	public RuleAction getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(RuleAction ruleAction) {
		this.ruleAction = ruleAction;
	}
}
