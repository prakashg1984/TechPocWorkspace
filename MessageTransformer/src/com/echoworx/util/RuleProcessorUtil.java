package com.echoworx.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.echoworx.rules.bo.MessageFileData;
import com.echoworx.rules.bo.RuleCondition;

public class RuleProcessorUtil {

	public static FileInputStream readFile(String fileName){
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fstream;
	}
	
	/**
	 * This method is used to read the Transformation Rule file
	 * @return
	 */
	public static List<RuleCondition> readTransformationLogic() {
		List<RuleCondition> ruleConditionList = new ArrayList<RuleCondition>();
		String fileLines;
		FileInputStream fstream = null;
		BufferedReader br = null;
		try {
			fstream = RuleProcessorUtil
					.readFile("props/transformationRule.props");
			br = new BufferedReader(new InputStreamReader(
					fstream));
			// To avoid the commented line
			br.readLine();
			while ((fileLines = br.readLine()) != null) {
				RuleCondition ruleCondition = RuleCondition
						.populateRuleCondition(fileLines);
				ruleConditionList.add(ruleCondition);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != fstream){
				try {
					fstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ruleConditionList;
	}
	
	/**
	 * This method is used to read a specific message input file based on the provided location
	 * @param fileName
	 * @return MessageFileData
	 */
	public static MessageFileData readFileData(String fileName) {
		//MessageFileData fileData = new MessageFileData();
		String fileLines;
		FileInputStream fstream = null;
		BufferedReader br = null;
		MessageFileData fileData = new MessageFileData();
		try {
			fstream = RuleProcessorUtil
					.readFile(fileName);
			br = new BufferedReader(new InputStreamReader(
					fstream));
			while ((fileLines = br.readLine()) != null) {
				//Populate data from file to object
				fileData = MessageFileData
						.populateFileData(fileData,fileLines);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != fstream){
				try {
					fstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fileData;
	}
	
	/**
	 * This method is used for specific replacement rule. It perform specific string replacement mentioned in requirement
	 * @param data
	 * @return
	 */
	public static String replaceString(String data){
		data = data.replace('$', 'e');
		data = data.replace('^', 'y');
		data = data.replace('&', 'u');
		return data;
	}
	
	/**
	 * This method is used to reverse a word based on the input data	
	 * @param data
	 * @return
	 */
	public static String reverseWord(String data){
		  String[] parts = data.split(" ");
		  StringBuffer sb = new StringBuffer();
		  for (String p : parts) {
			  if(p.contains("\n")){
				  String[] newLines = p.split("\n");
				  int i = 1;
				  for (String newLine : newLines) {
					  String reversed = new StringBuffer(newLine).reverse().toString();
					  sb.append(reversed);
					  sb.append(' ');
					  if(i++ < newLines.length){
						  sb.append("\n");
					  }
				  }
			  }else{
					String reversed = new StringBuffer(p).reverse().toString();
					sb.append(reversed);
				    sb.append(' ');
			  }
				
		   }
		  return sb.toString();
	}
}
