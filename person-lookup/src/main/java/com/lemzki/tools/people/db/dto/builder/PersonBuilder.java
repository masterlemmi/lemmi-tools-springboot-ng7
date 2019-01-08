package com.lemzki.tools.people.db.dto.builder;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.model.Relationship;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public interface PersonBuilder {
    PersonBuilder addChildren(Function<PersonDb, Collection<PersonDb>> childrenFunction);

    PersonBuilder addParents(Function<PersonDb, Collection<PersonDb>> parentFunction);

    PersonBuilder addSiblings(Function<PersonDb, Collection<PersonDb>> siblingFunction);

    PersonBuilder addRelationships(Function<PersonDb, Set<Relationship>> relationFunction);

    PersonDTO build();
}
