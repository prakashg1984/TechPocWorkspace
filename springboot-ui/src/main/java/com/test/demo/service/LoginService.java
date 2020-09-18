package com.test.demo.service;

import org.springframework.stereotype.Service;

import com.test.demo.bo.User;

@Service
public class LoginService {

	
	public boolean validateUser(User user) {
		return (user.getUserName()+"123").equalsIgnoreCase(user.getPassword());
	}
}
