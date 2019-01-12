package com.lemzki.tools.people.db.google.service;

import com.google.api.services.people.v1.PeopleService;
import com.lemzki.tools.people.db.model.PersonDb;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PeopleAPIBatchUpdater {

    boolean isUpdateInProgress();

    String getLogs();

    //@Async
    String update(PeopleService peopleService, PersonDb personDb) throws InterruptedException, ExecutionException;

    void update(PeopleService peopleService, List<List<PersonDb>> groups) throws InterruptedException;

}
