package com.pg.rest.repository;

import java.util.List;

import com.pg.model.Employee;

public interface EmployeeServiceRepo {

    public List<Employee> getAllEmployees();

    public Employee getEmployee(int id);

    public void updateEmployee(Employee employee, int id);

    public void deleteEmployee(int id);

    public void addEmployee(Employee employee);

}
