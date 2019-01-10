package com.lemzki.tools.people.db.service.google.impl;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.common.base.Stopwatch;
import com.lemzki.tools.result.Status;
import com.lemzki.tools.people.db.mapper.impl.GoogleContactUpdater;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.result.AsyncResult;
import com.lemzki.tools.people.db.service.google.PeopleApiSingleUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class PeopleApiSingleUpdaterImpl implements PeopleApiSingleUpdater {

    private final static String FIELDS = "names,relations,nicknames,birthdays,genders,events";
    private static final Logger logger = LogManager.getLogger(PeopleApiSingleUpdaterImpl.class);
    private PeopleService peopleService;

    @Override
    @Async
    public Future<AsyncResult<Person>> update(PersonDb personDb) {

        CompletableFuture<AsyncResult<Person>> completableFuture = new CompletableFuture<>();
        AsyncResult<Person> result = new AsyncResult<>();
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            Person contactToUpdate = peopleService.people()
                    .get(personDb.getResourceName())
                    .setPersonFields(FIELDS)
                    .execute();
            GoogleContactUpdater.updateContact(contactToUpdate, personDb);

            Person updatedPerson = peopleService.people()
                    .updateContact(contactToUpdate.getResourceName(), contactToUpdate)
                    .setUpdatePersonFields(FIELDS)
                    .execute();


            result.setStatus(Status.SUCCESS);
            result.setData(updatedPerson);
        } catch (Exception e) {
            result.setStatus(Status.FAIL);
            result.setError(e.getMessage());
            result.setData(null);
            logger.error("Error occurred while updating contact", e);
        }

        stopwatch.stop();
        result.setDuration(stopwatch.elapsed(TimeUnit.SECONDS));

        completableFuture.complete(result);
        return completableFuture;
    }
}
