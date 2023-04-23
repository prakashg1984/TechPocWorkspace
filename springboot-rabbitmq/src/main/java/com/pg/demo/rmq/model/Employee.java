package com.pg.demo.rmq.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee implements Serializable {

	@JsonProperty("empName")
	private String empName;
	
	@JsonProperty("empId")
	private String empId;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empId=" + empId + "]";
	}

}
