package com.pg.spring.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDepartmentDTO {
    private Integer userId;
    private String userName;
    private int age;
    private double salary;
    private Integer departmentId;
    private String departmentName;
    private String loc;
    
    public UserDepartmentDTO(UserDepartmentDTOBuilder builder) {
    	this.userId = builder.userId;
    	this.userName = builder.userName;
    	this.age = builder.age;
    	this.salary = builder.salary;
    	this.departmentId = builder.departmentId;
    	this.departmentName = builder.departmentName;
    	this.loc = builder.loc;
    }
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public static UserDepartmentDTOBuilder builder() {
		return new UserDepartmentDTOBuilder();
	}
	
	public static class UserDepartmentDTOBuilder {
		private Integer userId;
		private String userName;
		private int age;
		private double salary;
		private Integer departmentId;
		private String departmentName;
		private String loc;
		
		public UserDepartmentDTOBuilder() {
			
		};
		
		public UserDepartmentDTOBuilder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public UserDepartmentDTOBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public UserDepartmentDTOBuilder age(int age) {
			this.age = age;
			return this;

		}

		public UserDepartmentDTOBuilder salary(double salary) {
			this.salary = salary;
			return this;

		}

		public UserDepartmentDTOBuilder departmentId(Integer departmentId) {
			this.departmentId = departmentId;
			return this;

		}

		public UserDepartmentDTOBuilder departmentName(String departmentName) {
			this.departmentName = departmentName;
			return this;

		}

		public UserDepartmentDTOBuilder loc(String loc) {
			this.loc = loc;
			return this;

		}
		
		public UserDepartmentDTO build() {
			return new UserDepartmentDTO(this);
		}
	}

}
