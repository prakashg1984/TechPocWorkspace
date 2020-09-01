package com.pg.springjpa.web;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pg.springjpa.model.Name;
import com.pg.springjpa.service.NameService;

@RestController
@RequestMapping("/names")
public class NameController {
    @Autowired NameService service;

    @GetMapping
    public List<Name> getNames() {
        return service.getNames();
    }

    @PostMapping
    public void postNames(@RequestBody Name dto) {
        service.add(dto);
    }

    @GetMapping("/{id}")
    public Name getById(@PathVariable(required = true) long id) {
        return service.getNameById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
    
    @GetMapping("/name")
    @ResponseBody
    public ResponseEntity<Object> getNamesParam(@RequestParam String name) {
        MDC.put("userId", "http://www.SpringBootDev.com");
    	List<Name> names = service.getNameByName(name);
    	if(null != names && names.size() > 0) {
        	return ResponseEntity.status(HttpStatus.OK).body(names);
    	}else {
        	return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Matching Records");
    	}

    }
}