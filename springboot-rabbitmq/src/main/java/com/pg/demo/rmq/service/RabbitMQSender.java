package com.pg.demo.rmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pg.demo.rmq.model.Employee;

@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${my.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${my.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(Employee company) {
		rabbitTemplate.convertAndSend(exchange, routingkey, company);
		System.out.println("Send msg = " + company);
	    
	}
}
