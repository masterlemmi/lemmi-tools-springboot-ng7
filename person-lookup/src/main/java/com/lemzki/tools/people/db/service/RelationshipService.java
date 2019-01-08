package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.model.Relationship;

import java.util.Set;

public interface RelationshipService {

    Set<Relationship> findRelationsips(PersonDb personDb);

    void saveOrUpdate(Set<Relationship> relationships);
}
