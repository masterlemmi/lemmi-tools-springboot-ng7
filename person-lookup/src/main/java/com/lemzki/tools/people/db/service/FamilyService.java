package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.PersonDb;

import java.util.List;
import java.util.Set;

public interface FamilyService {

    List<PersonDb> findChildren(PersonDb personDb);

    Set<PersonDb> findParents(PersonDb personDb);

    List<PersonDb> findSiblings(PersonDb personDb);

    void saveOrUpdate(Family family);

    void saveOrUpdate(Set<Family> superFamily);


}
