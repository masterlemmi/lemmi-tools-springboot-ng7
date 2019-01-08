package com.lemzki.tools.people.db.dto.builder;

import com.lemzki.tools.people.db.dto.builder.impl.PersonBuilderImpl;
import com.lemzki.tools.people.db.model.PersonDb;

public class ResourceBuilder {

    public static PersonBuilder createPersonResource(PersonDb personDb) {
        return new PersonBuilderImpl(personDb);
    }
}
