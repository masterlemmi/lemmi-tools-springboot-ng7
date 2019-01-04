package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> find(long id);

    Person save(Person person);

    Person edit(Person person);

    void delete(long id);

    Set<Person> retrieveOrSave(Set<Person> persons);

    Person retrieveOrSave(Person person);

    List<Person> findByNameOrNickName(String nickNameOrName);
}
