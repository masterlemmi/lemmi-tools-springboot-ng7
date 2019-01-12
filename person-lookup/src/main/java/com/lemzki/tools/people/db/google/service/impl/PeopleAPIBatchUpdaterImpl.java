package com.lemzki.tools.people.db.google.service.impl;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.common.base.Stopwatch;
import com.lemzki.tools.people.db.google.service.PeopleAPIBatchUpdater;
import com.lemzki.tools.people.db.google.service.PeopleApiSingleUpdater;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.service.PersonService;
import com.lemzki.tools.result.AsyncResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PeopleAPIBatchUpdaterImpl implements PeopleAPIBatchUpdater {

    private AtomicBoolean updatInProgress = new AtomicBoolean(false);
    private static final Logger logger = LogManager.getLogger(PeopleAPIBatchUpdaterImpl.class);
    private final static String FIELDS = "urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders,events";

    @Autowired
    PeopleApiSingleUpdater singleUpdater;
    private StringBuffer logs = new StringBuffer();

    @Autowired
    PersonService personService;

    @Override
    public boolean isUpdateInProgress() {
        return updatInProgress.get();
    }

    @Override
    public String getLogs() {
        return logs.toString();
    }


    @Override
    //@Async
    public String update(PeopleService peopleService, PersonDb personDb) throws InterruptedException, ExecutionException {
        clearLogs();
        log("Started update on: " + LocalDateTime.now());
        updatInProgress.set(true);

        Stopwatch stopWatch = Stopwatch.createStarted();

        log("Starting update for person " + personDb.getName());

        //blocks
        AsyncResult<Person> result =  singleUpdater.update(peopleService, personDb).get();
        stopWatch.stop();
        long seconds = stopWatch.elapsed(TimeUnit.SECONDS);

        log("Update finished for " + seconds + " seconds");

        logger.debug("RESULTS: ", () -> result);

        TimeUnit.SECONDS.sleep(90);
        updatInProgress.set(false);

        log("Ended on: " + LocalDate.now());
        return getLogs();
    }


    @Override
    @Async
    public void update(PeopleService peopleService, List<List<PersonDb>> groups) throws InterruptedException {
        clearLogs();
        log("Started update on: " + LocalDateTime.now());
        updatInProgress.set(true);

        int done = 0;

        for (int i = 0; i < groups.size(); i++) {
            List<PersonDb> group = groups.get(i);
            Stopwatch stopWatch = Stopwatch.createStarted();

            log("Starting " + (i + 1) + "th group of " + group.size() + " people.");

            List<AsyncResult<Person>> results = updateBatchAndWaitForCompletion(peopleService, group);
            stopWatch.stop();
            long seconds = stopWatch.elapsed(TimeUnit.SECONDS);

            log((i + 1) + "th batch finished for " + seconds + " seconds");

            log("RESULTS: " + results.size());
            logger.debug("RESULTS: ", () -> results);

            TimeUnit.MINUTES.sleep(1);
        }
        updatInProgress.set(false);

        log("Ended on: " + LocalDate.now());
    }


    @SuppressWarnings("unchecked")
    private List<AsyncResult<Person>> updateBatchAndWaitForCompletion(PeopleService peopleService, List<PersonDb> group) {
        CompletableFuture[] resultss = new CompletableFuture[group.size()];

        for (int i = 0; i < group.size(); i++) {
            resultss[i] = singleUpdater.update(peopleService, group.get(i));
        }

        CompletableFuture.allOf(resultss);

        List<AsyncResult<Person>> finalResult = new ArrayList<>();
        for (CompletableFuture cf : resultss) {
            CompletableFuture<AsyncResult<Person>> res = (CompletableFuture<AsyncResult<Person>>) cf;
            try {
                AsyncResult<Person> data = res.get();
                if (data != null) {
                    finalResult.add(data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return finalResult;


//        //wait for batch to finish
//        return results.stream()
//                .filter(CompletableFuture.class::isInstance)
//                .map(CompletableFuture.class::cast)
//                .map(CompletableFuture::join)
//                .map(obj -> {
//                    return (AsyncResult<Person>) obj;
//                })
//                .collect(toList());
//
//    }


    }

    private void log(String log) {
        logs.append(log).append("\n");
        logger.info(log);
    }

    private void clearLogs() {
        this.logs = new StringBuffer();
    }
}