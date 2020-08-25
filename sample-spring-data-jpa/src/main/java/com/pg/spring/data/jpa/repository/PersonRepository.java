package com.pg.spring.data.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pg.spring.data.jpa.model.Person;

public interface PersonRepository<P> extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    
    List<Person> findByLastName(String lastName);
}
