package com.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.demo.redis.model.Person;
import com.demo.redis.repository.PersonRepository;

@SpringBootApplication
@EnableCaching
public class SimpleRedisApiApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final PersonRepository personRepository;

	@Autowired
	public SimpleRedisApiApplication(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleRedisApiApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		//Populating embedded database here
		LOG.info("Saving users. Current user count is {}.", personRepository.count());
		Person shubham = new Person("Shubham", 2000);
		Person pankaj = new Person("Pankaj", 29000);
		Person lewis = new Person("Lewis", 550);

		personRepository.save(shubham);
		personRepository.save(pankaj);
		personRepository.save(lewis);
		LOG.info("Done saving users. Data: {}.", personRepository.findAll());
		
	}

}
