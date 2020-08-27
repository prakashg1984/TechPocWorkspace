package com.pg.springjpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}