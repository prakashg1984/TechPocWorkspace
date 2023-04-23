package com.pg.demo.rmq.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pg.demo.rmq.model.Employee;

@Component
public class RabbitMQReceiver {

	private CountDownLatch latch = new CountDownLatch(1);
	
	private final String queueName = "my.queue";

	@RabbitListener(queues = queueName)
	public void receiveMessage(final Employee message) {
		System.out.println("Received <" + message + ">");

		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
