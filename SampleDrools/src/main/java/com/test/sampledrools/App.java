package com.test.sampledrools;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.test.model.RuleDetail;

public class App 
{
	public KieContainer kieContainer = null;
	
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        App app = new App();
        PopulateSampleData populateSampleData = new PopulateSampleData();
        
        app.loadKieSession(populateSampleData.populateSampleData());
        RuleResponse ruleResponse = app.runKieRools();
        app.writeOutputJson(ruleResponse);
    }
    
    public void loadKieSession(List<RuleDetail> ruleDetailList) {
    	try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

    		KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kfs = kieServices.newKieFileSystem();
            KieBuilder kieBuilder = null;
            
    		//String rule1 = FileReaderUtil.readFile(new File("src/main/resources/Rule1.drl"));
    		//String rule2 = FileReaderUtil.readFile(new File("src/main/resources/Rule2.drl"));
    		//String rule3 = FileReaderUtil.readFile(new File("src/main/resources/Rule3.drl"));

            //kfs.write("src/main/resources/Rule1.drl", rule1);
            //kfs.write("src/main/resources/Rule2.drl", rule2);
            //kfs.write("src/main/resources/Rule3.drl", rule3);
            
            for(RuleDetail ruleDetail : ruleDetailList) {
    			Template t = ve.getTemplate("src/main/resources/" + ruleDetail.getRuleType() + ".vm");

				VelocityContext context = new VelocityContext();
				context.put("ruleDetail", ruleDetail);	
				StringWriter writer = new StringWriter();
				t.merge(context, writer);
				
				System.out.println(ruleDetail.getRuleName() + " : " + writer.toString());
				kfs.write("src/main/resources/"+ruleDetail.getRuleName()+".drl", writer.toString());
            }

            kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
            
            kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
            Results results = kieBuilder.getResults();
            System.out.println("results "+results);

    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    }
    
    public RuleResponse runKieRools() {
        StatelessKieSession kieSession = null;
        RuleResponse ruleResponse = new RuleResponse();

        try {
        	String request = FileReaderUtil.readFile(new File("src/main/resources/request.json"));
    		String response = FileReaderUtil.readFile(new File("src/main/resources/response.json"));
            
            kieSession = kieContainer.newStatelessKieSession();
            List cmds = new ArrayList();
            Map<String, String> orderMap = new HashMap<String,String>();
            orderMap.put("request", request);
            orderMap.put("response", response);

            cmds.add(CommandFactory.newInsert(orderMap));
            cmds.add(CommandFactory.newSetGlobal("myUtil", new Util()));
            cmds.add(CommandFactory.newInsert(ruleResponse));

            kieSession.execute(CommandFactory.newBatchExecution(cmds));
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
        return ruleResponse;
    }
    
    public void writeOutputJson(RuleResponse ruleResponse) {
    	try {
    		DocumentContext documentContext =  JsonPath.parse(new File("src/main/resources/request.json"));
        	for(Map<String,Object> ruleRes : ruleResponse.getRulesList()) {
        		ruleRes.forEach((key, value) -> {
        			String position = key.substring(0, key.lastIndexOf("."));
        			String field = key.substring(key.lastIndexOf(".")+1,key.length());
        			documentContext.put("$."+position, field, value);
        			});
            }
        	
    		System.out.println(documentContext.jsonString());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		
    }
    		
}
