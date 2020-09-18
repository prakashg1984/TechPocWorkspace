package com.pg.springjpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pg.springjpa.model.Person;
import com.pg.springjpa.service.PersonService;


@RestController
@RequestMapping("/jpa")
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person getAllUsers(@PathVariable Long id) {
		return personService.getById(id);
	}

	@RequestMapping(value = "/personByName/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getPersoneByName(@PathVariable String name) {
		return personService.findByName(name);
	}
	
	@RequestMapping(value = "/personByLastName/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getPersoneByLastName(@PathVariable String name) {
		return personService.findByLastName(name);
	}

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getAll() {
		return personService.getAllPersons();
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public HttpStatus deletePersnone(@PathVariable Long id) {
		personService.deletePerson(id);
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	@ResponseBody
	public HttpStatus insertPersone(@RequestBody Person person) {
		return personService.addPerson(person) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	@ResponseBody
	public HttpStatus updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
	}
}
