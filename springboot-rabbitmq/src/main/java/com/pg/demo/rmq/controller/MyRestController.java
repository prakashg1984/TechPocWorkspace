package com.pg.demo.rmq.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.demo.rmq.model.Employee;
import com.pg.demo.rmq.service.RabbitMQReceiver;
import com.pg.demo.rmq.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class MyRestController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Autowired
	RabbitMQReceiver rabbitMQReceiver;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		rabbitMQSender.send(emp);
		
		try {
			rabbitMQReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Message sent to the RabbitMQ Successfully";
	}
}
