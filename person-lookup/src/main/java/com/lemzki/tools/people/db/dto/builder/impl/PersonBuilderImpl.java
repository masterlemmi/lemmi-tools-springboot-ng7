package com.lemzki.tools.people.db.dto.builder.impl;

import com.google.common.collect.Sets;
import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.dto.builder.PersonBuilder;
import com.lemzki.tools.people.db.enums.Gender;
import com.lemzki.tools.people.db.mapper.impl.PersonMapper;
import com.lemzki.tools.people.db.mapper.impl.RelationshipMapper;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.model.Relationship;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class PersonBuilderImpl implements PersonBuilder {

    private Person person;
    private Function<Person, Collection<Person>> retrieveChildren = p -> new ArrayList<>();
    private Function<Person, Collection<Person>> retrieveParents = p -> new HashSet<>();
    private Function<Person, Collection<Person>> retrieveSiblings = p -> new ArrayList<>();
    private Function<Person, Set<Relationship>> retrieveRelationships = p -> new HashSet<>();


    public PersonBuilderImpl(Person person) {
        this.person = person;
    }

    @Override
    public PersonBuilder addChildren(Function<Person, Collection<Person>> childrenFunction) {
        this.retrieveChildren = childrenFunction;
        return this;
    }

    @Override
    public PersonBuilder addParents(Function<Person, Collection<Person>> parentFunction) {
        this.retrieveParents = parentFunction;
        return this;
    }

    @Override
    public PersonBuilder addSiblings(Function<Person, Collection<Person>> siblingFunction) {
        this.retrieveSiblings = siblingFunction;
        return this;
    }

    @Override
    public PersonBuilder addRelationships(Function<Person, Set<Relationship>> relationFunction) {
        this.retrieveRelationships = relationFunction;
        return this;
    }

    @Override
    public PersonDTO build() {
        Set<PersonDTO> parents = Sets.newHashSet(personRetriever.apply(retrieveParents));
        List<PersonDTO> children = personRetriever.apply(retrieveChildren);
        Map<String, Set<PersonDTO>> relationships = relationshipRetriever.apply(retrieveRelationships);

        List<PersonDTO> siblings = personRetriever.apply(retrieveSiblings);

        ComplexPersonDTO complexPerson = (ComplexPersonDTO) PersonMapper.toResource(person);
        complexPerson.setParents(parents);
        complexPerson.setRelationships(relationships);
        complexPerson.setSiblings(siblings);
        complexPerson.setChildren(children);

        return complexPerson;
    }


    private Function<Function<Person, Collection<Person>>, List<PersonDTO>> personRetriever =
            retrieveFn -> retrieveFn
                    .apply(person)
                    .stream()
                    .map(PersonMapper::toSimpleResource)
                    .collect(toList());

    private Function<Function<Person, Set<Relationship>>, Map<String, Set<PersonDTO>>> relationshipRetriever =
            retrieverFn -> retrieverFn.andThen(RelationshipMapper::mapModel).apply(person);
}
