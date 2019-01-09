package com.lemzki.tools.people.db.service.impl;


import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.EmailAddress;
import com.google.api.services.people.v1.model.Person;
import com.lemzki.tools.people.db.mapper.Result;
import com.lemzki.tools.people.db.mapper.impl.GoogleContactUpdater;
import com.lemzki.tools.people.db.mapper.impl.GoogleResourceMapper;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.service.PeopleAPIService;
import com.lemzki.tools.people.db.service.PersonService;
import com.lemzki.tools.security.LoggedInUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final static String UPDATE_FIELDS = "names,relations," +
            "nicknames,birthdays,genders,events";
    private static final Integer PAGE_SIZE = 2000;

    @Autowired
    PeopleService peopleService;

    @Autowired
    LoggedInUser loggedInUser;

    @Autowired
    PersonService personService;

    private List<Person> getList() {
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
    public String exportContactsToGoogle() {
        List<PersonDb> people = personService.findAll();
        long success = people.stream().map(personDb -> {
            Person p = null;
            try {
                Person contactToUpdate = peopleService.people().get(personDb.getResourceName()).setPersonFields(FIELDS).execute();
                GoogleContactUpdater.updateContact(contactToUpdate, personDb);

                p = peopleService.people()
                        .updateContact(contactToUpdate.getResourceName(), contactToUpdate)
                        .setUpdatePersonFields(FIELDS)
                        .execute();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;
        }).filter(Objects::nonNull)
                .count();

        return "Exported: " + success + "/" + people.size();

    }

    @Override
    public void syncLocalDBWithAPI() {

    }
}