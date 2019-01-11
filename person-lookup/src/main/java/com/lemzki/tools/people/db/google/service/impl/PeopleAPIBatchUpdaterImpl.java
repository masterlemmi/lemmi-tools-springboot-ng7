package com.lemzki.tools.people.db.google.service.impl;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.common.base.Stopwatch;
import com.lemzki.tools.people.db.google.service.PeopleAPIBatchUpdater;
import com.lemzki.tools.people.db.google.service.PeopleApiSingleUpdater;
import com.lemzki.tools.people.db.mapper.Result;
import com.lemzki.tools.people.db.model.PersonDb;
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

    @Autowired
    PeopleApiSingleUpdater singleUpdater;
    private StringBuffer logs = new StringBuffer();

    @Override
    public boolean isUpdateInProgress() {
        return updatInProgress.get();
    }

    @Override
    public String getLogs() {
        return logs.toString();
    }

    @Override
    @Async
    public void update(PeopleService peopleService, List<List<PersonDb>> groups) throws InterruptedException {
        clearLogs();
       log("Started update on: " + LocalDate.now());
        updatInProgress.set(true);

        int done = 0;

        for (int i = 0; i < groups.size(); i++) {
            List<PersonDb> group = groups.get(i);
            Stopwatch stopWatch = Stopwatch.createStarted();
            logger.info("Starting " + (i + 1) + "th batch.");
            log("Starting " + (i + 1) + "th batch.");

            List<AsyncResult<Person>> results = updateBatchAndWaitForCompletion(peopleService, group);
            stopWatch.stop();
            long seconds = stopWatch.elapsed(TimeUnit.SECONDS);
            logger.info((i + 1) + "th batch finished for " + seconds + " seconds");
            log((i + 1) + "th batch finished for " + seconds + " seconds");
            logger.info("RESULTS: " + results.size());
            log("RESULTS: " + results.size());
            results.forEach(res -> {
                logger.info("\tSTATUS: {}", res.getStatus());
                log("\tSTATUS: " + res.getStatus());
                logger.info("\tError: {}", res.getError());
                logger.info("\tDuration: {}", res.getDuration());
            });

            System.out.println("--NOw Wiat for 30 seconds");
            TimeUnit.SECONDS.sleep(30);
        }
        updatInProgress.set(false);
        logger.info("DONE");
        log("Ended on: " + LocalDate.now());
    }



    @SuppressWarnings("unchecked")
    private List<AsyncResult<Person>> updateBatchAndWaitForCompletion(PeopleService peopleService, List<PersonDb> group) {
        List<Future<AsyncResult<Person>>> results = new ArrayList<>();
        for (PersonDb personDb : group) {
            results.add(singleUpdater.update(peopleService, personDb));
        }

        List<AsyncResult<Person>> lists = new ArrayList<>();
        for (Future<AsyncResult<Person>> res : results) {

            try {
                AsyncResult<Person> r = res.get();
                lists.add(r);
            } catch (Exception e) {
                System.out.println("ERROR" + e);
            }

        }

        System.out.println("LIST");
        return lists;


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
    private void log(String log){
        logs.append(log);
    }

    private void clearLogs(){
        this.logs = new StringBuffer();
    }
}