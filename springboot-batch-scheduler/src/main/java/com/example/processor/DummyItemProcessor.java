package com.example.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.bo.Person;

public class DummyItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(DummyItemProcessor.class);

	@Override
	public Person process(Person person) throws Exception {
		log.info("Inside DummyItemProcessor "+person);
		return person;
	}

}
