package com.lemzki.tools.people.db.dto.builder.impl;

import com.google.common.collect.Sets;
import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.dto.builder.PersonBuilder;
import com.lemzki.tools.people.db.mapper.impl.PersonMapper;
import com.lemzki.tools.people.db.mapper.impl.RelationshipMapper;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.model.Relationship;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class PersonBuilderImpl implements PersonBuilder {

    private PersonDb personDb;
    private Function<PersonDb, Collection<PersonDb>> retrieveChildren = p -> new ArrayList<>();
    private Function<PersonDb, Collection<PersonDb>> retrieveParents = p -> new HashSet<>();
    private Function<PersonDb, Collection<PersonDb>> retrieveSiblings = p -> new ArrayList<>();
    private Function<PersonDb, Set<Relationship>> retrieveRelationships = p -> new HashSet<>();


    public PersonBuilderImpl(PersonDb personDb) {
        this.personDb = personDb;
    }

    @Override
    public PersonBuilder addChildren(Function<PersonDb, Collection<PersonDb>> childrenFunction) {
        this.retrieveChildren = childrenFunction;
        return this;
    }

    @Override
    public PersonBuilder addParents(Function<PersonDb, Collection<PersonDb>> parentFunction) {
        this.retrieveParents = parentFunction;
        return this;
    }

    @Override
    public PersonBuilder addSiblings(Function<PersonDb, Collection<PersonDb>> siblingFunction) {
        this.retrieveSiblings = siblingFunction;
        return this;
    }

    @Override
    public PersonBuilder addRelationships(Function<PersonDb, Set<Relationship>> relationFunction) {
        this.retrieveRelationships = relationFunction;
        return this;
    }

    @Override
    public PersonDTO build() {
        Set<PersonDTO> parents = Sets.newHashSet(personRetriever.apply(retrieveParents));
        List<PersonDTO> children = personRetriever.apply(retrieveChildren);
        Map<String, Set<PersonDTO>> relationships = relationshipRetriever.apply(retrieveRelationships);

        List<PersonDTO> siblings = personRetriever.apply(retrieveSiblings);

        ComplexPersonDTO complexPerson = (ComplexPersonDTO) PersonMapper.toResource(personDb);
        complexPerson.setParents(parents);
        complexPerson.setRelationships(relationships);
        complexPerson.setSiblings(siblings);
        complexPerson.setChildren(children);

        return complexPerson;
    }


    private Function<Function<PersonDb, Collection<PersonDb>>, List<PersonDTO>> personRetriever =
            retrieveFn -> retrieveFn
                    .apply(personDb)
                    .stream()
                    .map(PersonMapper::toSimpleResource)
                    .collect(toList());

    private Function<Function<PersonDb, Set<Relationship>>, Map<String, Set<PersonDTO>>> relationshipRetriever =
            retrieverFn -> retrieverFn.andThen(RelationshipMapper::mapModel).apply(personDb);
}
