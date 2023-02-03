package com.example.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.bo.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{
	
	  private static final Logger LOGGER =
		      LoggerFactory.getLogger(EmployeeProcessor.class);
	
  public Employee process(Employee employee) throws Exception{
      LOGGER.info("Inserting employee '{}'", employee);
      return employee;
  }
}