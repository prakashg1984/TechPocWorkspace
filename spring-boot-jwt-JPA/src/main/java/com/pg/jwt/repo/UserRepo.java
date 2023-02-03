package com.pg.jwt.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pg.jwt.entity.UserDAO;

@Repository
public interface UserRepo extends CrudRepository<UserDAO, Integer> {
	
	UserDAO findByUsername(String username);
	
}