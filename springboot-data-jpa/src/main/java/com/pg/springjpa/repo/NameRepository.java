package com.pg.springjpa.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pg.springjpa.model.Name;

@Repository
public interface NameRepository extends CrudRepository<Name, Long> {
    List<Name> findByName(String name);
    
}