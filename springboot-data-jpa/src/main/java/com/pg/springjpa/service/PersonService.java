package com.pg.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.springjpa.model.Person;
import com.pg.springjpa.repo.PersonRepository;


@Service
public class PersonService {

	@Autowired
	PersonRepository<Person> personRepository;

	@Transactional
	public List<Person> getAllPersons() {
		return (List<Person>) personRepository.findAll();
	}

	@Transactional
	public List<Person> findByName(String name) {
		return personRepository.findByFirstName(name);
	}
	
	@Transactional
	public List<Person> findByLastName(String name) {
		return personRepository.findByLastName(name);
	}

	@Transactional
	public Person getById(Long id) {
		return personRepository.findById(id).get() ;
	}

	@Transactional
	public void deletePerson(Long personId) {
		personRepository.delete(personRepository.findById(personId).get());
	}

	@Transactional
	public boolean addPerson(Person person) {
		return personRepository.save(person) != null;
	}

	@Transactional
	public boolean updatePerson(Person person) {
		return personRepository.save(person) != null;
	}
}
