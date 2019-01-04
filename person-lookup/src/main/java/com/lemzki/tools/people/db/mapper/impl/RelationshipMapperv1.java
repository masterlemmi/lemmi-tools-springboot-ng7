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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component("relationMapper")
public class RelationshipMapper implements ResourceMapper<Map<String, Set<PersonDTO>>, Map<Person, Relationship>> {

    @Autowired
    ResourceMapper<PersonDTO, Person> sPersonMapper;

    @Autowired
    RelationTypeRepository relationTypeRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Map<Person, Relationship> mapResource(Map<String, Set<PersonDTO>> relationsByType) {

        Map<Person, Relationship> relationships = new HashMap<>();

        relationsByType.entrySet().forEach(entry -> {
            Set<PersonDTO> sPersons = entry.getValue();
            RelationType type = getRelationType(entry.getKey());

            sPersons.forEach(sPerson -> {
                if (sPerson.getId() == null) {
                    //new person, retrieveOrSave it
                    Person person = sPersonMapper.mapResource(sPerson);
                    relationships.put(person, Relationship.of(type));
                } else {
                    //existing person update or retrieveOrSave
                    updateExistingPerson(relationships, type, sPerson);
                }
            });

        });
        return relationships;
    }

    private void updateExistingPerson(Map<Person, Relationship> relationships, RelationType type, PersonDTO sPerson) {

        Optional<Person> optionalPerson = personRepository.findById(sPerson.getId());

        if (optionalPerson.isPresent()) {
            Person personKey = optionalPerson.get();

            if (relationships.containsKey(personKey)) {
                relationships.get(personKey).getRelation().save(type);
            } else {
                relationships.put(optionalPerson.get(), Relationship.of(type));
            }
        } else {
            Person newPerson = sPersonMapper.mapResource(sPerson);
            relationships.put(newPerson, Relationship.of(type));
        }
    }

    private RelationType getRelationType(String relName) {
        RelationType type = relationTypeRepository.findByName(relName);
        return type != null ? type : new RelationType(relName);
    }

    @Override
    public Map<String, Set<PersonDTO>> mapModel(Map<Person, Relationship> relationships) {

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
