package com.pg.springjpa.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pg.springjpa.model.Name;
import com.pg.springjpa.repo.NameRepository;

@Component
public class NameService {
	
    private static final Logger logger = LoggerFactory.getLogger(NameService.class);

    @Autowired 
    NameRepository repository;

    public void add(Name dto) {
        repository.save(dto);
    }

    public void delete(long id) {
    	Optional<Name> optionalName = repository.findById(id);
        repository.delete(optionalName.get());
    }

    public List<Name> getNames() {
        return (List<Name>) repository.findAll();
    }

    public Name getNameById(long id) {
    	logger.info("Input for getNameById {} ",id);
    	Optional<Name> optionalName = repository.findById(id);
        return optionalName.get();
    }

    public List<Name> getNameByName(String name) {
    	logger.info("Input for getNameByName {} ",name);
        return (List<Name>) repository.findByName(name);
    }
}