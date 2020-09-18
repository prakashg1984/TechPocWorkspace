package com.demo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DemoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");// setting the content type
		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");
		pw.println("Welcome to servlet");
		pw.println("</body></html>");

		pw.close();// closing the stream
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject finalJsonObject = new JSONObject(); 
		JSONArray finalJsonArray = new JSONArray(); 
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("doPost "+jb.toString());
		
		
        try {
			Object obj = new JSONParser().parse(jb.toString());
	        JSONObject jo = (JSONObject) obj; 
	        JSONArray dataArray = (JSONArray) jo.get("data");
	        
	        for (Object data : dataArray) {
	        	JSONObject dataObject = (JSONObject) data;
	        	JSONArray progressArray = (JSONArray) dataObject.get("progress");
	        	
	        	 for (Object progress : progressArray) {
	        		 JSONObject progressObject = (JSONObject) progress;
	        		 String content_status = (String)progressObject.get("content_status");
	        		 long percent_completed = (long)progressObject.get("percent_completed");
	        		 
	        		 System.out.println("content_status "+content_status);
	        		 System.out.println("percent_completed "+percent_completed);
	        		 
         			 JSONObject statusObject = new JSONObject(); 
         			statusObject.put("content_status", content_status);
         			statusObject.put("percent_completed", percent_completed);
         			finalJsonArray.add(statusObject);

	        	 }
	        }
	        finalJsonObject.put("FinalStatus", finalJsonArray);
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(finalJsonObject.toJSONString());
		response.getWriter().flush();
		response.getWriter().close();

	}

}
