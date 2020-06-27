package com.att.pg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SampleKafkaConsumer {
		
	@Autowired 
	KafkaConsumerBeanTest kafkaConsumerBean;
	
	@Autowired
	SampleKafkaProducer kafkaProducer;
	
	/**
	 * This method can be used if we need to switch on/off the subscriber based on flag
	 */
	@PostConstruct
	public void runConsumer() {
		kafkaConsumerBean.runConsumer();
		
		while(true) {
			DateFormat dateFormat = new SimpleDateFormat("HH");
			Calendar cal = Calendar.getInstance();
			String hour = dateFormat.format(cal.getTime());
			
			if(hour.equalsIgnoreCase("23") || hour.equalsIgnoreCase("24") || 
					hour.equalsIgnoreCase("01") || hour.equalsIgnoreCase("02")) {
				//do nothing
			}else {
				kafkaProducer.sendMessage("Test");
			}
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
