package com.lemzki.tools.people.db.service.impl;


import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.repository.PersonRepository;
import com.lemzki.tools.people.db.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> find(long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person edit(Person personResource) {
        return this.save(personResource);
    }


    @Override
    public void delete(long id) {
        personRepository.deleteById(id);
    }


    //retrieve the persons if in db.. retrieveOrSave if not.. purpose is for each person returned to have their own ids
    @Override
    public Set<Person> retrieveOrSave(Set<Person> persons) {

        Set<Person> results = new HashSet<>();

        for (Person person: persons){
            Person p = this.retrieveOrSave(person);
            results.add(p);
        }
        return results;

    }

    @Override
    public List<Person> findByNameOrNickName(String nickNameOrName) {
        return personRepository.findByNameContainingIgnoreCaseOrNicknameContainingIgnoreCase(nickNameOrName, nickNameOrName);
    }

    //retrieveOrSave if no id. retrieve if there is. if no result. retrieveOrSave again
    @Override
    public Person retrieveOrSave(Person person) {
        return person.getId() == null ?
                this.save(person) : this.find(person.getId()).orElse(this.save(person));
    }
}
