package com.pg.springjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pg.springjpa.model.Name;
import com.pg.springjpa.repo.NameRepository;

@Component
public class NameService {
    @Autowired NameRepository repository;

    public void add(Name dto) {
        repository.save(dto);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public List<Name> getNames() {
        return (List<Name>) repository.findAll();
    }

    public Name getNameById(long id) {
    	Name optionalName = repository.findOne(id);
        return optionalName;
    }

   /* private Dog toEntity(DogDto dto) {
        Dog entity = new Dog();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        return entity;
    }*/
}