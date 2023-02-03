package com.pg.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public Application(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) {

		//Populating embedded database here
		LOG.info("Saving users. Current user count is {}.", userRepository.count());
		User shubham = new User("Shubham", 2000);
		User pankaj = new User("Pankaj", 29000);
		User lewis = new User("Lewis", 550);

		userRepository.save(shubham);
		userRepository.save(pankaj);
		userRepository.save(lewis);
		LOG.info("Done saving users. Data: {}.", userRepository.findAll());
	}
}
