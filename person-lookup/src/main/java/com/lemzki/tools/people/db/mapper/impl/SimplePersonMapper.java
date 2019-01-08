/*
package com.lemzki.tools.people.db.mapper.impl;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sPersonMapper")
public class SimplePersonMapper implements ResourceMapper<PersonDTO, PersonDb> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonDb mapResource(PersonDTO simplePerson) {
        if(simplePerson.getId() == null){
            return newPerson(simplePerson);
        } else {
            return personRepository.findById(simplePerson.getId()).orElse(newPerson(simplePerson));
        }
    }

    private PersonDb newPerson(PersonDTO simplePerson) {
        PersonDb person = new PersonDb();
        person.setId(simplePerson.getId());
        person.setName(simplePerson.getName());
        person.setGender(simplePerson.getGender());
        return person;
    }

    @Override
    public PersonDTO mapModel(PersonDb person) {
        return new PersonDTO(person.getName(), person.getGender(), person.getId());
    }
}*/
