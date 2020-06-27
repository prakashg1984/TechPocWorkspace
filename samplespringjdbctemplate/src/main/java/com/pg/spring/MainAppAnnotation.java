package com.pg.spring;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppAnnotation {

	public static void main(String[] args)  {
		Connection conn = null;
		try {
			conn = SampleSqlLite.createNewDatabase("test.db");
			if (conn != null) {
				SampleSqlLite.createTableCategories(conn);
				SampleSqlLite.insertDataCategories(conn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		doTestSpringJdbc();
	}
	
	private static void doTestSpringJdbc() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	    context.register(AppConfig.class);
	    context.refresh();
	    DataSource dataSource = (DataSource)context.getBean("dataSource");
	    
	    TestJDBCTemplate testJDBCTemplate = 
		         (TestJDBCTemplate)context.getBean("testJDBCTemplate");
	    
	   
	    testJDBCTemplate.setDataSource(dataSource);
		testJDBCTemplate.getStudent(1);
		
		TestJDBCTemplateConstructor testJDBCTemplateConstructor =
				(TestJDBCTemplateConstructor)context.getBean("testJDBCTemplateConstructor");
		testJDBCTemplateConstructor.getStudent(1);
		
		context.close();
	}
}
