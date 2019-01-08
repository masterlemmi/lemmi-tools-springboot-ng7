package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.model.PersonDb;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {

    List<PersonDb> findAll();

    Optional<PersonDb> find(long id);

    PersonDb save(PersonDb personDb);

    List<PersonDb> saveAll(Set<PersonDb> personDb);

    PersonDb edit(PersonDb personDb);

    void delete(long id);

    Set<PersonDb> retrieveOrSave(Set<PersonDb> personDbs);

    PersonDb retrieveOrSave(PersonDb personDb);

    List<PersonDb> findByNameOrNickName(String nickNameOrName);
}
