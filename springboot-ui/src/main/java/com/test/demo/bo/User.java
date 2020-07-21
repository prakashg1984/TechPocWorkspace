package com.test.demo.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	@NotNull
	@Size(min=2 , max=10)
	private String userName;
	
	@NotNull
	@Size(min=2 , max=10)
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

}
