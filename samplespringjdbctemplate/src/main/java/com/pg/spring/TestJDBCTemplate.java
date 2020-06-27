package com.pg.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestJDBCTemplate {

	@Autowired
	SampleController sampleController;
     private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      //this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	   
	   public void getStudent(Integer id) {
		      String SQL = "select category_name from da_te_categories where category_id = 1";
		      String category_name =	jdbcTemplateObject.queryForObject(SQL, 
		         String.class);
		      
		      System.out.println("category_name "+category_name);
		      
		      sampleController.sampleMethod();
		   }
}
