package com.echoworx.main;

import java.util.List;
import java.util.logging.Logger;

import com.echoworx.rules.bo.MessageFileData;
import com.echoworx.rules.bo.RuleCondition;
import com.echoworx.rules.process.MessageTransformRuleProcessorImpl;
import com.echoworx.rules.process.RuleProcessor;
import com.echoworx.util.RuleProcessorUtil;

/**
 * Main Runner Class for File Transformation
 * @author PGPS
 *
 */
public class RunTransformerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Processing RunTransformerMain");
		RuleProcessor ruleProcessor = new MessageTransformRuleProcessorImpl();
		try {
			List<RuleCondition> ruleConditionList = RuleProcessorUtil.readTransformationLogic();
			List<MessageFileData> fileDataList = ruleProcessor.identifyRuleInput("props\\input");
			ruleProcessor.processRules(ruleConditionList,fileDataList);
			ruleProcessor.writeProcessedOutPut(fileDataList);
			System.out.println("Completed Processing RunTransformerMain");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
