package com.example.demo;

import java.util.Iterator;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enter Input");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.println("input "+input);

        employeeRepository.save(new Employee("Ramesh", "Fadatare", "ramesh@gmail.com"));
        employeeRepository.save(new Employee("Tom", "Cruise", "tom@gmail.com"));
        employeeRepository.save(new Employee("John", "Cena", "john@gmail.com"));
        employeeRepository.save(new Employee("tony", "stark", "stark@gmail.com"));

        logger.info("# of employees: {}", employeeRepository.count());

        Iterable < Employee > employees = employeeRepository.findAll();
        Iterator < Employee > iterator = employees.iterator();
        while (iterator.hasNext()) {
            logger.info("{}", iterator.next().toString());
        }

        logger.info("------------------------");

        logger.info("Deleting employee with id 1");
        employeeRepository.deleteById(Long.valueOf(1));

        logger.info("# of employees: {}", employeeRepository.count());

        employeeRepository.existsById(Long.valueOf(2));
        employeeRepository.findById(Long.valueOf(2));

	}
}
