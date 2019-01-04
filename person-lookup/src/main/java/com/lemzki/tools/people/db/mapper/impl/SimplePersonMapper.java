/*
package com.lemzki.tools.people.db.mapper.impl;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sPersonMapper")
public class SimplePersonMapper implements ResourceMapper<PersonDTO, Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person mapResource(PersonDTO simplePerson) {
        if(simplePerson.getId() == null){
            return newPerson(simplePerson);
        } else {
            return personRepository.findById(simplePerson.getId()).orElse(newPerson(simplePerson));
        }
    }

    private Person newPerson(PersonDTO simplePerson) {
        Person person = new Person();
        person.setId(simplePerson.getId());
        person.setName(simplePerson.getName());
        person.setGender(simplePerson.getGender());
        return person;
    }

    @Override
    public PersonDTO mapModel(Person person) {
        return new PersonDTO(person.getName(), person.getGender(), person.getId());
    }
}*/
