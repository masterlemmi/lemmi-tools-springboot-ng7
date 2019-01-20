package com.lemzki.tools.people.db.service.impl;


import com.lemzki.tools.people.db.model.PersonDb;
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
    public List<PersonDb> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<PersonDb> find(long id) {
        return personRepository.findById(id);
    }

    @Override
    public PersonDb save(PersonDb personDb) {
        return personRepository.save(personDb);
    }

    @Override
    public List<PersonDb> saveAll(Set<PersonDb> personDb) {
        return personRepository.saveAll(personDb);
    }

    @Override
    public PersonDb edit(PersonDb personDbResource) {
        return this.save(personDbResource);
    }

    @Override
    public void delete(long id) {
        personRepository.deleteById(id);
    }


    //retrieve the personDbs if in db.. retrieveOrSave if not.. purpose is for each person returned to have their own ids
    @Override
    public Set<PersonDb> retrieveOrSave(Set<PersonDb> personDbs) {

        Set<PersonDb> results = new HashSet<>();

        for (PersonDb personDb : personDbs){
            PersonDb p = this.retrieveOrSave(personDb);
            results.add(p);
        }
        return results;

    }

    @Override
    public List<PersonDb> findByNameOrNickName(String nickNameOrName) {
        return personRepository.findByFirstNameContainingIgnoreCaseOrNicknameContainingIgnoreCase(nickNameOrName, nickNameOrName);
    }

    //retrieveOrSave if no id. retrieve if there is. if no result. retrieveOrSave again
    @Override
    public PersonDb retrieveOrSave(PersonDb personDb) {
        return personDb.getId() == null ?
                this.save(personDb) : this.find(personDb.getId()).orElse(this.save(personDb));
    }

    @Override
    public List<PersonDb> saveAllByResourceName(Set<PersonDb> passed) {
        passed.stream()
                .forEach(personDb -> {
                    Optional<PersonDb> optionalPersonDb = personRepository.findByResourceName(personDb.getResourceName());

                    if(optionalPersonDb.isPresent()){
                       personDb.setId(optionalPersonDb.get().getId());
                    }

                    personRepository.save(personDb);

                });
        return null;
    }
}
