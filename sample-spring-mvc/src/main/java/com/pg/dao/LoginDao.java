package com.pg.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {

	boolean createUser(String userName,String password,String emailAddress);
	
	Map checkUser(String userName,String password);
}
