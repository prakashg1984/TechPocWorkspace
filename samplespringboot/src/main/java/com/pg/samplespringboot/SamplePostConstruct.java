package com.pg.samplespringboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SamplePostConstruct {

	@Autowired
	SampleBean sampleBean;
	
	@PostConstruct
	public void runPostConstruct() {
		for(int i=0;i<100;i++) {
			sampleBean.executeBean();
		}
	}
}
