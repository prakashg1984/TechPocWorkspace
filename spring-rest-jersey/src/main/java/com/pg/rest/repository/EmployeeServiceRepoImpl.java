package com.pg.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pg.model.Employee;

@Repository
public class EmployeeServiceRepoImpl implements EmployeeServiceRepo {

    private List<Employee> employeeList;

    public EmployeeServiceRepoImpl() {
        employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Jane"));
        employeeList.add(new Employee(2, "Jack"));
        employeeList.add(new Employee(3, "George"));
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Employee getEmployee(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public void updateEmployee(Employee employee, int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                emp.setId(employee.getId());
                emp.setFirstName(employee.getFirstName());
                return;
            }
        }
    }

    public void deleteEmployee(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                employeeList.remove(emp);
                return;
            }
        }
    }

    public void addEmployee(Employee employee) {
        for (Employee emp : employeeList) {
            if (emp.getId() == employee.getId()) {
            }
        }
        employeeList.add(employee);
    }

}
