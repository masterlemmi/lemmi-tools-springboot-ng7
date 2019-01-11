package com.lemzki.tools.people.db.google.service;

import com.google.api.services.people.v1.PeopleService;
import com.lemzki.tools.people.db.model.PersonDb;

import java.util.List;

public interface PeopleAPIBatchUpdater {

    boolean isUpdateInProgress();

    String getLogs();

    void update(PeopleService peopleService, List<List<PersonDb>> groups) throws InterruptedException;

}
