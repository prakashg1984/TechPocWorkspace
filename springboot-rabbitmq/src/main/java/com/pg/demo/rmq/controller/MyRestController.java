package com.pg.demo.rmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.demo.rmq.model.Employee;
import com.pg.demo.rmq.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class MyRestController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	
	Employee emp=new Employee();
	emp.setEmpId(empId);
	emp.setEmpName(empName);
		rabbitMQSender.send(emp);

		return "Message sent to the RabbitMQ Successfully";
	}
}
