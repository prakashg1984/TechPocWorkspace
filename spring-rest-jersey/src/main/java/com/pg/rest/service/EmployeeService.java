package com.pg.rest.service;

import java.util.List;

import com.pg.model.Employee;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee getEmployee(int id);

    public void updateEmployee(Employee employee, int id);

    public void deleteEmployee(int id);

    public void addEmployee(Employee employee);

}
