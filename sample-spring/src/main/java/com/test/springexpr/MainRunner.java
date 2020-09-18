package com.test.springexpr;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

public class MainRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	    context.register(AppConfig.class);	    
	    context.refresh();

	    try {
			Resource res = context.getResource("META-INF/spring/app-context.xml");
			InputStream is = res.getInputStream();
			String content = IOUtils.toString(is, Charset.defaultCharset());
			System.out.println("content "+content);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ServiceImpl exampleService = (ServiceImpl)context.getBean("serviceImpl");
	    
	    System.out.println(exampleService.getMessage());
	    
		context.close();

	}

}
