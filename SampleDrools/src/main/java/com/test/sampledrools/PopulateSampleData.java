package com.test.sampledrools;

import java.util.ArrayList;
import java.util.List;

import com.test.model.RuleAction;
import com.test.model.RuleCondition;
import com.test.model.RuleDetail;

public class PopulateSampleData {

	public List<RuleDetail> populateSampleData() {
		List<RuleDetail> ruleDetailsList = new ArrayList<RuleDetail>();
		
		
		//Rule 1
		List<RuleCondition> ruleConditionList1 = new ArrayList<>();
		RuleCondition ruleCondition1 = new RuleCondition();
		ruleCondition1.setSource("response");
		ruleCondition1.setField("contactDetail");
		ruleConditionList1.add(ruleCondition1);
		
		RuleAction ruleAction1 = new RuleAction();
		ruleAction1.setSource("response");
		ruleAction1.setField("contactDetail");
		ruleAction1.setTargetPosition("order.statusDetail.contactDetail");
		ruleAction1.setDefaultValue("false");
		
		RuleDetail ruleDetail1 = new RuleDetail();
		ruleDetail1.setRuleId("1");
		ruleDetail1.setRuleName("CONTACT_DETAIL_EXIST_CHECK");
		ruleDetail1.setRuleType("FIELD_EXIST_CHECK");
		ruleDetail1.setRuleConditionList(ruleConditionList1);
		ruleDetail1.setRuleAction(ruleAction1);
		
		ruleDetailsList.add(ruleDetail1);
		
		//Rule 2
		List<RuleCondition> ruleConditionList2 = new ArrayList<>();
		RuleCondition ruleCondition2 = new RuleCondition();
		ruleCondition2.setSource("response");
		ruleCondition2.setField("orderSource");
		ruleConditionList2.add(ruleCondition2);
		
		ruleCondition2 = new RuleCondition();
		ruleCondition2.setSource("request");
		ruleCondition2.setField("notes");
		ruleConditionList2.add(ruleCondition2);
		
		RuleAction ruleAction2 = new RuleAction();
		ruleAction2.setSource("response");
		ruleAction2.setField("orderSource");
		ruleAction2.setTargetPosition("order.orderSource");
		ruleAction2.setDefaultValue("DUMMY_SOURCE");
		
		RuleDetail ruleDetail2 = new RuleDetail();
		ruleDetail2.setRuleId("1");
		ruleDetail2.setRuleName("ORDERSOURCE_EXIST_CHECK");
		ruleDetail2.setRuleType("FIELD_EXIST_CHECK");
		ruleDetail2.setRuleConditionList(ruleConditionList2);
		ruleDetail2.setRuleAction(ruleAction2);
		
		ruleDetailsList.add(ruleDetail2);
		
		//Rule 3
		List<RuleCondition> ruleConditionList3 = new ArrayList<>();
		RuleCondition ruleCondition3 = new RuleCondition();
		ruleCondition3.setSource("response");
		ruleCondition3.setField("paymentMethod");
		ruleConditionList3.add(ruleCondition3);
		
		ruleCondition3 = new RuleCondition();
		ruleCondition3.setSource("response");
		ruleCondition3.setField("salesAgent");
		ruleConditionList3.add(ruleCondition3);
		
		RuleAction ruleAction3 = new RuleAction();
		ruleAction3.setSource("response");
		ruleAction3.setField("notfoundvalue");
		ruleAction3.setTargetPosition("order.notfoundvalue");
		ruleAction3.setDefaultValue("True");
		
		RuleDetail ruleDetail3 = new RuleDetail();
		ruleDetail3.setRuleId("1");
		ruleDetail3.setRuleName("AGENT_PAYMENT_EXIST_CHECK");
		ruleDetail3.setRuleType("FIELD_EXIST_CHECK");
		ruleDetail3.setRuleConditionList(ruleConditionList3);
		ruleDetail3.setRuleAction(ruleAction3);
		
		ruleDetailsList.add(ruleDetail3);

		return ruleDetailsList;
	}
}
