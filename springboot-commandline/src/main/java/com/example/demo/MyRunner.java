package com.example.demo;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeService employeeService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enter Input");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.println("input "+input);

		for(int i=0;i<Integer.valueOf(input);i++) {
			employeeService.insertIntoDB(i);
		}
       
		sc.close();
		logger.info("MAIN # of employees: {}", employeeRepository.count());

	}
	
	
}
