package com.demo.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.redis.model.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
