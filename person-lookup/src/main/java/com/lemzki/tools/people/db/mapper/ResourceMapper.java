package com.lemzki.tools.people.db.mapper;



public interface ResourceMapper<T,R> {

    default ResourceMapper<T, R> createFrom(R r) {
        return this;
    }

    R mapResource(T t);

    T mapModel(R r);


}
