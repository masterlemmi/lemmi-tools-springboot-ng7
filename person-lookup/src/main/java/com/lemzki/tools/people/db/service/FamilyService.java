package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.Person;

import java.util.List;
import java.util.Set;

public interface FamilyService {

    List<Person> findChildren(Person person);

    Set<Person> findParents(Person person);

    List<Person> findSiblings(Person person);

    void saveOrUpdate(Family family);

    void saveOrUpdate(Set<Family> superFamily);


}
