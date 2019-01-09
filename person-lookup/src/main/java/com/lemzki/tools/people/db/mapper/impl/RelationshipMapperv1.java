/*
package com.lemzki.tools.people.db.mapper.impl;

import com.google.common.collect.Sets;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.mapper.ResourceMapper;
import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.model.Relationship;
import com.lemzki.tools.people.db.repository.RelationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.com.lemzki.tools.security.util.HashMap;
import java.com.lemzki.tools.security.util.Map;
import java.com.lemzki.tools.security.util.Optional;
import java.com.lemzki.tools.security.util.Set;

@Component("relationMapper")
public class RelationshipMapper implements ResourceMapper<Map<String, Set<PersonDTO>>, Map<PersonDb, Relationship>> {

    @Autowired
    ResourceMapper<PersonDTO, PersonDb> sPersonMapper;

    @Autowired
    RelationTypeRepository relationTypeRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Map<PersonDb, Relationship> mapResource(Map<String, Set<PersonDTO>> relationsByType) {

        Map<PersonDb, Relationship> relationships = new HashMap<>();

        relationsByType.entrySet().forEach(entry -> {
            Set<PersonDTO> sPersons = entry.getValue();
            RelationType type = getRelationType(entry.getKey());

            sPersons.forEach(sPerson -> {
                if (sPerson.getId() == null) {
                    //new person, retrieveOrSave it
                    PersonDb person = sPersonMapper.mapResource(sPerson);
                    relationships.put(person, Relationship.of(type));
                } else {
                    //existing person update or retrieveOrSave
                    updateExistingPerson(relationships, type, sPerson);
                }
            });

        });
        return relationships;
    }

    private void updateExistingPerson(Map<PersonDb, Relationship> relationships, RelationType type, PersonDTO sPerson) {

        Optional<PersonDb> optionalPerson = personRepository.findById(sPerson.getId());

        if (optionalPerson.isPresent()) {
            PersonDb personKey = optionalPerson.get();

            if (relationships.containsKey(personKey)) {
                relationships.get(personKey).getRelation().save(type);
            } else {
                relationships.put(optionalPerson.get(), Relationship.of(type));
            }
        } else {
            PersonDb newPerson = sPersonMapper.mapResource(sPerson);
            relationships.put(newPerson, Relationship.of(type));
        }
    }

    private RelationType getRelationType(String relName) {
        RelationType type = relationTypeRepository.findByName(relName);
        return type != null ? type : new RelationType(relName);
    }

    @Override
    public Map<String, Set<PersonDTO>> mapModel(Map<PersonDb, Relationship> relationships) {

        Map<String, Set<PersonDTO>> relationsByType = new HashMap<>();

        relationships.entrySet().forEach(entry -> {
            PersonDTO other = sPersonMapper.mapModel(entry.getKey());
            Set<RelationType> relationTypes = entry.getValue().getRelation();
            relationTypes.forEach(type -> {
                if (relationsByType.containsKey(type.getName())) {
                    relationsByType.get(type.getName()).save(other);
                } else {
                    relationsByType.put(type.getName(), Sets.newHashSet(other));
                }
            });
        });

        return relationsByType;
    }
}*/
