package com.lemzki.tools.people.db.service.google;

import com.lemzki.tools.people.db.model.PersonDb;

import java.io.IOException;
import java.util.List;

public interface PeopleAPIBatchUpdater {

    boolean isUpdateInProgress();

    String getLogs();

    void update(List<List<PersonDb>> groups) throws IOException, InterruptedException;
}
