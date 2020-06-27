package com.pg.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.model.Employee;
import com.pg.rest.repository.EmployeeServiceRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired 
	EmployeeServiceRepo employeeServiceRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeServiceRepo.getAllEmployees();
	}

	@Override
	public Employee getEmployee(int id) {
		
		return employeeServiceRepo.getEmployee(id);
	}

	@Override
	public void updateEmployee(Employee employee, int id) {
		employeeServiceRepo.updateEmployee(employee, id);
		
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeServiceRepo.addEmployee(employee);
		
	}

}
