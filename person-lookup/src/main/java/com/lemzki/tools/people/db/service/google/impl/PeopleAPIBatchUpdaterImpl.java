package com.lemzki.tools.people.db.service.google.impl;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.common.base.Stopwatch;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.service.google.PeopleAPIBatchUpdater;
import com.lemzki.tools.people.db.service.google.PeopleApiSingleUpdater;
import com.lemzki.tools.result.AsyncResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.toList;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PeopleAPIBatchUpdaterImpl implements PeopleAPIBatchUpdater {

    private AtomicBoolean updatInProgress = new AtomicBoolean(false);
    private static final Logger logger = LogManager.getLogger(PeopleAPIBatchUpdaterImpl.class);
    private final static String FIELDS = "urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders,events";

    PeopleService peopleService;

    @Autowired
    PeopleApiSingleUpdater singleUpdater;

    private StringBuffer logs = new StringBuffer();


    @Override
    @Async
    public void update(List<List<PersonDb>> groups) throws InterruptedException {
        updatInProgress.set(true);

        int done = 0;

        for (int i = 0; i<groups.size(); i++) {
            List<PersonDb> group = groups.get(i);
            Stopwatch stopWatch = Stopwatch.createStarted();
            logger.info("Starting " + (i+1) +  "th batch.");
            List<AsyncResult<Person>> results = updateBatchAndWaitForCompletion(group);
            stopWatch.stop();
            long seconds = stopWatch.elapsed(TimeUnit.SECONDS);
            logger.info((i+1) +  "th batch finished for " + seconds);
            logger.info("RESULTS:");
            results.forEach(res->{
                logger.info("STATUS:", res.getStatus());
                logger.info("Error:", res.getError());
                logger.info("Duration:", res.getDuration());
            });
            TimeUnit.MINUTES.sleep(1);
        }
        updatInProgress.set(false);
    }

    @SuppressWarnings("unchecked")
    private List<AsyncResult<Person>> updateBatchAndWaitForCompletion(List<PersonDb> group) {
        List<Future<AsyncResult<Person>>> results = new ArrayList<>();
        for (PersonDb personDb : group) {
            results.add(singleUpdater.update(personDb));
        }

        //wait for batch to finish
        return results.stream()
                .filter(CompletableFuture.class::isInstance)
                .map(CompletableFuture.class::cast)
                .map(CompletableFuture::join)
                .map(obj->{
                    return (AsyncResult<Person>)obj;
                })
                .collect(toList());

    }


    @Override
    public boolean isUpdateInProgress() {
        return updatInProgress.get();
    }

    @Override
    public String getLogs() {

        String status = "Update in Progress. 3/350 done";

        return status + logs;
    }
}
