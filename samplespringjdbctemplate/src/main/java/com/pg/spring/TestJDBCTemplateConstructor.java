package com.pg.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestJDBCTemplateConstructor {

	@Autowired
	SampleController sampleController;
    private JdbcTemplate jdbcTemplateObject;
    
	public TestJDBCTemplateConstructor(DataSource dataSource){
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	 public void getStudent(Integer id) {
	      String SQL = "select category_group from da_te_categories where category_id = 1";
	      String category_group =	jdbcTemplateObject.queryForObject(SQL, 
	         String.class);
	      
	      System.out.println("category_group "+category_group);
	      
	      sampleController.sampleMethod();
	   }
}
