package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.dto.RelationDTO;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.model.Relationship;

import java.util.Map;
import java.util.Set;

public interface RelationshipService {

    Set<Relationship> findRelationsips(Person person);

    void saveOrUpdate(Set<Relationship> relationships);
}
