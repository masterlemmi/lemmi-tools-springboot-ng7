package com.lemzki.tools.people.db.google.service;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.result.AsyncResult;

import java.util.concurrent.Future;

public interface PeopleApiSingleUpdater {

    Future<AsyncResult<Person>> update(PeopleService peopleService, PersonDb personDb);

}
