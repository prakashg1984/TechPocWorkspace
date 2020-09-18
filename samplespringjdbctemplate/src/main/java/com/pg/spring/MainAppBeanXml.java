package com.pg.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppBeanXml {
	
	public static void main(String[] args) {
		doTestSpringJdbc();
	}
	
	private static void doTestSpringJdbc() {

		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF\\spring\\bean.xml");

		TestJDBCTemplate testJDBCTemplate = 
		         (TestJDBCTemplate)context.getBean("testJDBCTemplate");
		
		testJDBCTemplate.getStudent(1);
		
		TestJDBCTemplateConstructor testJDBCTemplateConstructor =
				(TestJDBCTemplateConstructor)context.getBean("testJDBCTemplateConstructor");
		testJDBCTemplateConstructor.getStudent(1);
	
	}
}
