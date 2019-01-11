package com.lemzki.tools.people.db.google.service.impl;


import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.lemzki.tools.people.db.google.service.PeopleAPIBatchUpdater;
import com.lemzki.tools.people.db.google.service.PeopleAPIService;
import com.lemzki.tools.people.db.google.service.PeopleServiceRetriever;
import com.lemzki.tools.people.db.mapper.Result;
import com.lemzki.tools.people.db.mapper.impl.GoogleResourceMapper;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.service.PersonService;
import com.lemzki.tools.security.LoggedInUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toSet;

@Service
public class PeopleAPIServiceImpl implements PeopleAPIService {

    private static final Logger logger = LogManager.getLogger(PeopleAPIServiceImpl.class);

    private final static String ME = "people/me";
    private final static String FIELDS = "urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders,events";

    private static final Integer PAGE_SIZE = 2000;

    @Autowired
    LoggedInUser loggedInUser;

    @Autowired
    PersonService personService;

    @Autowired
    PeopleAPIBatchUpdater peopleAPIBatchUpdater;

    @Autowired
    PeopleServiceRetriever peopleServiceFacade;

    @Value("${google.people.api.write.limit}")
    int limit;

    private List<Person> getList() {
        PeopleService peopleService = peopleServiceFacade.get();
        List<Person> list = new ArrayList<>();
        try {
            list = peopleService.people().connections().list(ME).setPageSize(PAGE_SIZE)
                    .setPersonFields(FIELDS)
                    .execute().getConnections();
        } catch (Exception e) {
            System.out.println("UNable to retrieve contacts from google" + e.getMessage());
        }

        return list;
    }

    @Override
    public String importContactPersonsFromGoogle() {

        long currentUser = loggedInUser.get().getId();
        List<Person> people = getList();

        Map<Boolean, List<Result>> mappedResource = people.stream()
                .map(GoogleResourceMapper::map)
                .collect(partitioningBy(Result::passed));

        Set<PersonDb> passed = mappedResource.get(true).stream()
                .map(Result::get)
                .peek(person -> {
                            person.setAddedBy(currentUser);
                            person.setUser(loggedInUser.get());
                        }
                )
                .collect(toSet());

        personService.saveAll(passed);

        String successMessage = "Imported " + passed.size() + "/" + people.size() + " contacts";
        String failedMessage = "Failed to import the ff: " + listFailures(mappedResource.get(false));
        return successMessage + "/n" + failedMessage;
    }

    private String listFailures(List<Result> results) {
        return results.stream()
                .map(result -> {
                    String name = result.get().getNickname();
                    String error = result.error();
                    return "ERROR: " + error + " Name: " + name;
                }).collect(Collectors.joining("/n"));
    }


    @Override
    public String exportContactsToGoogle() throws InterruptedException {

        if (peopleAPIBatchUpdater.isUpdateInProgress()) {
            return peopleAPIBatchUpdater.getLogs();
        }


        //FOR TESTING CUT LIST To 35 and limit to 10
        List<PersonDb> list = personService.findAll().subList(0, 35);
        this.limit = 10;


        if (CollectionUtils.isEmpty(list)) {
            logger.info("PersonList is Empty. Nothing to Export");
            return "No People to expoort";
        }

        List<List<PersonDb>> groups = splitListByGoogleLimit(list);

        // hand it off to updater and return

        PeopleService peopleService = peopleServiceFacade.get();
        peopleAPIBatchUpdater.update(peopleService, groups);

        int estimateDuration = list.size() / limit;
        return "Handed off to Updater Service for Asynchronous processing. Estimate Duration:" + estimateDuration + " mins.";

    }

    @Override
    public void syncLocalDBWithAPI() throws InterruptedException {

    }

    private List<List<PersonDb>> splitListByGoogleLimit(List<PersonDb> list) {
        List<List<PersonDb>> splitPeople = new ArrayList<>();
        //split the people to the google limit
        for (int i = 0, len = list.size(); i < len; i += limit) {
            int end = i + limit > len ? len : i + limit;
            splitPeople.add(list.subList(i, end));
        }
        return splitPeople;
    }
}