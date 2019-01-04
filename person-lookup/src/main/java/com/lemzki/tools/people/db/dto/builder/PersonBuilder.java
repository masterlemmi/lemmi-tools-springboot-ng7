package com.lemzki.tools.people.db.dto.builder;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.model.Relationship;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface PersonBuilder {
    PersonBuilder addChildren(Function<Person, Collection<Person>> childrenFunction);

    PersonBuilder addParents(Function<Person, Collection<Person>> parentFunction);

    PersonBuilder addSiblings(Function<Person, Collection<Person>> siblingFunction);

    PersonBuilder addRelationships(Function<Person, Set<Relationship>> relationFunction);

    PersonDTO build();
}
