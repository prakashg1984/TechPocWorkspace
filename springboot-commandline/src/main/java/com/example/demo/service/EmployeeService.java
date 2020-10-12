package com.example.demo.service;

import java.util.Iterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.MyRunner;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    
	@Async("threadPoolExecutor")
	public void insertIntoDB(int count) {
		logger.info("Currently Executing thread number {} ; name {} ",count, Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeRepository.save(new Employee("Name_"+count, "LN", count+"_Email@gmail.com"));

		logger.info("# of employees in insertIntoDB: {}", employeeRepository.count());

		Iterable<Employee> employees = employeeRepository.findAll();
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			logger.info("{}", iterator.next().toString());
		}

		boolean found = employeeRepository.existsById(Long.valueOf(count+1));
		
		Optional<Employee> employee =  employeeRepository.findById(Long.valueOf(count+1));
		
		logger.info("found employees: {} ; {} ", found,employee);
		
		employeeRepository.deleteById(Long.valueOf(count+1));

	}
}
