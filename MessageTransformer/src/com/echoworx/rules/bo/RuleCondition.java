package com.echoworx.rules.bo;

import java.util.Arrays;
import java.util.List;
/**
 * Data object to hold the rule condition details
 * @author PGPS
 *
 */
public class RuleCondition {

	private String field;
	private String value;
	private String position;
	private String specialCondition;
	private String[] rulesToBeExecuted;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSpecialCondition() {
		return specialCondition;
	}
	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
	}
	public String[] getRulesToBeExecuted() {
		return rulesToBeExecuted;
	}
	public void setRulesToBeExecuted(String[] rulesToBeExecuted) {
		this.rulesToBeExecuted = rulesToBeExecuted;
	}
	
	/**
	 * This method is used to populate the Object based on the input Transformation rule file
	 * @param conditionData
	 * @return RuleCondition
	 */
	public static RuleCondition populateRuleCondition(String conditionData){
		RuleCondition ruleCondition = new RuleCondition();
		String ruleToBeExecuted;
		ruleCondition.setField(conditionData.split(",")[0]);
		ruleCondition.setValue(conditionData.split(",")[1]);
		ruleCondition.setPosition(conditionData.split(",")[2]);
		ruleCondition.setSpecialCondition(conditionData.split(",")[3]);
		
		ruleToBeExecuted = conditionData.split(",")[4];
		
		ruleCondition.setRulesToBeExecuted(ruleToBeExecuted.split("-"));
		return ruleCondition;
	}
	@Override
	public String toString() {
		return "RuleCondition [field=" + field + ", value=" + value
				+ ", position=" + position + ", specialCondition="
				+ specialCondition + ", rulesToBeExecuted="
				+ Arrays.toString(rulesToBeExecuted) + "]";
	}
	
}
