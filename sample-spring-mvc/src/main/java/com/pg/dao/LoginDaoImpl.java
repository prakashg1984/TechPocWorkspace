package com.pg.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {

	JdbcTemplate jdbcTemplate;
	
	/*private final String SQL_INSERT_USER = "insert into user_details(user_id, password, email_address) values(?,?,?)";
	private final String SQL_SELECT_USER = "select * from user_details where user_id = ? and password = ?";*/
	@Autowired
	Environment environment;
	
	@Autowired
	public LoginDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean createUser(String userName, String password, String emailAddress) {
		jdbcTemplate.update(environment.getProperty("SQL_INSERT_USER"),userName,password,emailAddress);
		return true;
	}

	public Map checkUser(String userName, String password) {
		Map resultMap = null;
		try {
			resultMap = jdbcTemplate.queryForObject(environment.getProperty("SQL_SELECT_USER"),new Object[] {userName,password} ,new GenericRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

}
