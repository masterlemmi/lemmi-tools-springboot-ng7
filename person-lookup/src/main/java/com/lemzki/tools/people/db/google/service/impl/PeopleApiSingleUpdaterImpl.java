package com.lemzki.tools.people.db.google.service.impl;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.common.base.Stopwatch;
import com.lemzki.tools.people.db.google.service.PeopleApiSingleUpdater;
import com.lemzki.tools.people.db.service.PersonService;
import com.lemzki.tools.result.Status;
import com.lemzki.tools.people.db.mapper.impl.GoogleContactUpdater;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.result.AsyncResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class PeopleApiSingleUpdaterImpl implements PeopleApiSingleUpdater {

    private final static String FIELDS = "names,relations,nicknames,birthdays,genders,events";
    private static final Logger logger = LogManager.getLogger(PeopleApiSingleUpdaterImpl.class);

    @Autowired
    PersonService personService;

    @Override
    @Async("googleUpdaterTaskExecutor")
    public CompletableFuture<AsyncResult<Person>> update(PeopleService peopleService, PersonDb personDb) {

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
            personDb.setSynched(true);
        } catch (Exception e) {
            personDb.setSynched(false);
            result.setStatus(Status.FAIL);
            result.setError(e.getMessage());
            result.setData(null);
            logger.error("Error occurred while updating contact " +  personDb.getId() + ": " + personDb.getName(), e);
        }

        stopwatch.stop();
        result.setDuration(stopwatch.elapsed(TimeUnit.SECONDS));
        personService.save(personDb); //saves sync status

        completableFuture.complete(result);
        return completableFuture;
    }
}
