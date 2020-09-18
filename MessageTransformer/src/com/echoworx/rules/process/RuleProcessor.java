package com.echoworx.rules.process;

import java.util.List;

import com.echoworx.rules.bo.MessageFileData;
import com.echoworx.rules.bo.RuleCondition;

/**
 * Interface for Rule Processing
 * @author PGPS
 *
 */
public interface RuleProcessor {

	public void processRules(List<RuleCondition> ruleConditionList,List<MessageFileData> fileDataList);
	public List<MessageFileData> identifyRuleInput(String filePath);
	public void writeProcessedOutPut(List<MessageFileData> fileDataList);
}
