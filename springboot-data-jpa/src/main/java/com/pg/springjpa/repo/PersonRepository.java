package com.pg.springjpa.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pg.springjpa.model.Person;


@Repository
public interface PersonRepository<P> extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    
    List<Person> findByLastName(String lastName);
}
