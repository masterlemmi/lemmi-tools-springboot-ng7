package com.lemzki.tools.people.db.dto.builder;

import com.lemzki.tools.people.db.dto.builder.impl.PersonBuilderImpl;
import com.lemzki.tools.people.db.model.Person;

public class ResourceBuilder {

    public static PersonBuilder createPersonResource(Person person) {
        return new PersonBuilderImpl(person);
    }
}
