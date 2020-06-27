package com.echoworx.rules.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.echoworx.rules.bo.MessageFileData;
import com.echoworx.rules.bo.RuleCondition;
import com.echoworx.util.MessageTransformerConstants;
import com.echoworx.util.RuleProcessorUtil;
/**
 * Implementation for rule processing.
 * This Ruleprocessor will do Message Transformation based on given set of conditions against set of input files
 * @author PGPS
 *
 */
public class MessageTransformRuleProcessorImpl implements RuleProcessor {

	/**
	 * This method is used to process the Rules based on input rule condition
	 * @param ruleConditionList
	 * @param fileDataList
	 * 
	 */
	@Override
	public void processRules(List<RuleCondition> ruleConditionList,List<MessageFileData> fileDataList){
		
		//Loop through each file input
		for(MessageFileData fileData : fileDataList){
			//For Each of the input file, execute all the rules in ruleset
			System.out.println("Processing File : "+fileData.getFileName());
			for(RuleCondition ruleCondition : ruleConditionList){
				processIndividualRule(ruleCondition, fileData);
			}
		}
	}

	/**
	 * This method is used to read all the Input data file from a given path
	 * @param filePath
	 * @return List of Input File Data
	 */
	@Override
	public List<MessageFileData> identifyRuleInput(String filePath) {
		List<MessageFileData> fileDataList = new ArrayList<MessageFileData>();
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        MessageFileData fileData = RuleProcessorUtil.readFileData(filePath + File.separator +file.getName());
		        fileData.setFileName(file.getName());
		        fileDataList.add(fileData);
		    }
		}
		return fileDataList;
	}
	
	/**
	 * This method is used to process each individual Rule mentioned in the rule property file for one file input
	 * This method currently executes one Special Condition and other Generic conditions
	 * Generic conditions are those which follows a field match pattern. All others are Special Condition 
	 * @param ruleCondition
	 * @param fileData
	 */
	private void processIndividualRule(RuleCondition ruleCondition,MessageFileData fileData){
		String[] rulesToBeExecuted=ruleCondition.getRulesToBeExecuted();
		//Check if any special condition is defined - If so give that priority
		if(StringUtils.isNotBlank(ruleCondition.getSpecialCondition())){
			String field = ruleCondition.getField();
			String data = StringUtils.EMPTY;
			if(field.equalsIgnoreCase(MessageTransformerConstants.BODY)){
				data = fileData.getBody();
			}else if(field.equalsIgnoreCase(MessageTransformerConstants.TO)){
				data = fileData.getTo();
			}else if(field.equalsIgnoreCase(MessageTransformerConstants.FROM)){
				data = fileData.getFrom();
			}else if(field.equalsIgnoreCase(MessageTransformerConstants.SUBJECT)){
				data = fileData.getSubject();
			}
			//Check if the Condition satisfies for the given data
			boolean conditionSatisfied = checkIfConditionSatisfies(data, null, null, ruleCondition.getSpecialCondition());
			if(conditionSatisfied){
				executeRule(rulesToBeExecuted,fileData);
			}
		}
		//Check for all other generic conditions defined based on field match
		if(StringUtils.isNotBlank(ruleCondition.getField())){
			String field=ruleCondition.getField();
			String data = StringUtils.EMPTY;
			if(field.equalsIgnoreCase(MessageTransformerConstants.BODY)){
				data = fileData.getBody();
			}else if(field.equalsIgnoreCase(MessageTransformerConstants.TO)){
				data = fileData.getTo();
			}else if(field.equalsIgnoreCase(MessageTransformerConstants.FROM)){
				data = fileData.getFrom();
			}else if(field.equalsIgnoreCase(MessageTransformerConstants.SUBJECT)){
				data = fileData.getSubject();
			}
			//Check if the Condition satisfies for the given data
			boolean conditionSatisfied = checkIfConditionSatisfies(data,ruleCondition.getPosition(), ruleCondition.getValue(), null);
			if(conditionSatisfied){
				executeRule(rulesToBeExecuted,fileData);
			}
		}
	}
	
	/**
	 * This method is used to check if each of the Condition mentioned in the Rule properties is satisfied
	 * @param data
	 * @param positon
	 * @param value
	 * @param specialCondn
	 * @return boolean
	 */
	private boolean checkIfConditionSatisfies(String data,String positon,String value,String specialCondn){
		boolean conditionSatisfied=false;
		if(StringUtils.isNotBlank(specialCondn)){
			//Currently only one special condition is handled - Need specific handling for each special condition
			if(specialCondn.equalsIgnoreCase(MessageTransformerConstants.TEN_DIGIT_RULE)){
				final Pattern p = Pattern.compile( "(\\d{10})" );
				final Matcher m = p.matcher( data );
				if (m.find()) {
					conditionSatisfied = true;
				}
			}
		}else{
			//Handling for all Generic condtions which are based on pattern match
			if(StringUtils.isNotBlank(positon)){
				switch(positon){
				case "ANY":
					if(data.contains(value)){
						conditionSatisfied = true;
					}
					break;
				case "START":
					if(data.startsWith(value)){
						conditionSatisfied = true;
					}
					break;
				case "END":
					if(data.endsWith(value)){
						conditionSatisfied = true;
					}
					break;
				default:
					conditionSatisfied = false;
					break;
				}
			}
		}
		return conditionSatisfied;
	}
	
	/**
	 * This method is used to execute the specific TransformationLogic mentioned against a rule if the rule is satisfied
	 * Only 2 rules will be supported currently - REPLACEMENT and REVERSAL
	 * @param rulesToBeExecuted
	 * @param fileData
	 */
	private void executeRule(String[] rulesToBeExecuted,MessageFileData fileData){
		String data = fileData.getBody();
		for(String ruleToBeExecuted : rulesToBeExecuted){
			if(ruleToBeExecuted.equalsIgnoreCase(MessageTransformerConstants.REPLACEMENT_RULE)){
				data = RuleProcessorUtil.replaceString(data);
			}else if(ruleToBeExecuted.equalsIgnoreCase(MessageTransformerConstants.REVERSAL_RULE)){
				data = RuleProcessorUtil.reverseWord(data);
			}
		}
		
		fileData.setBody(data);
	}

	/**
	 * This method is used to write the final output after all transformation to an output folder
	 * @param fileDataList
	 */
	@Override
	public void writeProcessedOutPut(List<MessageFileData> fileDataList) {
		for (MessageFileData fileData : fileDataList) {
			String fileOutPutPath = "props\\output\\";
			System.out.println("Writing Processed Output for file " + fileData.getFileName() + " at location "+fileOutPutPath);
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(fileOutPutPath
						+ fileData.getFileName()));
				writer.append("To:" + fileData.getTo() + "\n");
				writer.append("From:" + fileData.getFrom() + "\n");
				writer.append("Subject:" + fileData.getSubject() + "\n");
				writer.append("Body:" + "\n" + fileData.getBody() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
				}
			}
		}

	}
	
	
}
