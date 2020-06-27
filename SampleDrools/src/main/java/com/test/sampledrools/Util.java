package com.test.sampledrools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Util {

	public static Properties prop = new Properties();
	public static InputStream input = null;

	public Util() {
		try {
			input = new FileInputStream("src/main/resources/jsonpath.props");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkFieldPresent(Map order,String source,String field) {
		String payload = "";
		
		payload = order.get(source).toString();
		DocumentContext documentContext =  JsonPath.parse(payload);
		String jsonPath = prop.getProperty(field);
		
		try {
				Object jsonField = documentContext.read(jsonPath);
				System.out.println("Property Found " + field + " : " + jsonPath + " : "+jsonField);
				return true;
			
		}catch(Exception e) {
			System.out.println("Property Not found for "+ field + " : "+jsonPath);
		}
		return false;
	}
	
	public Map findData(Map order,String source,String field,String fieldPosition,String defaultValue) {
		String payload = "";
		Map<String,Object> responseMap;
		
		payload = order.get(source).toString();
		System.out.println(payload);
		DocumentContext documentContext =  JsonPath.parse(payload);
		String jsonPath = prop.getProperty(field);
		
		try {
			Object jsonField = documentContext.read(jsonPath);
			System.out.println("Property Found " + jsonField);
			responseMap = new HashMap<String,Object>();
			responseMap.put(fieldPosition, jsonField);
		}catch(Exception e) {
			System.out.println("Property Not found for "+jsonPath);
			responseMap = new HashMap<String,Object>();
			responseMap.put(fieldPosition, defaultValue);
		}
		return responseMap;
	}
	
	public void log(String message) {
		System.out.println(message);
	}
}
