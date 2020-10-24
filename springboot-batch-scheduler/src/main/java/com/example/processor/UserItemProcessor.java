package com.example.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.bo.Employee;

public class UserItemProcessor implements ItemProcessor<Employee, Employee> {

	 @Override
	 public Employee process(Employee user) throws Exception {
	  return user;
	 }

	} 