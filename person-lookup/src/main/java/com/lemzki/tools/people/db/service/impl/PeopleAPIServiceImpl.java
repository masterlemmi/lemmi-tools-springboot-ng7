package com.lemzki.tools.people.db.service.impl;


import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.lemzki.tools.people.db.mapper.Result;
import com.lemzki.tools.people.db.mapper.impl.GoogleResourceMapper;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.service.PeopleAPIService;
import com.lemzki.tools.people.db.service.PersonService;
import com.lemzki.tools.security.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

@Service
public class PeopleAPIServiceImpl implements PeopleAPIService {

    private final static String ME = "people/me";
    private final static String FIELDS = "urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders";
    private static final Integer PAGE_SIZE = 2000;

    @Autowired
    PeopleService peopleService;

    @Autowired
    LoggedInUser loggedInUser;

    @Autowired
    PersonService personService;

    private List<Person> getList()  {
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
    public void importContactPersons() {
        List<Person> people = getList();
        long currentUser = loggedInUser.getUser().getId();
        Map<Boolean, List<Result>> mappingResult = people.stream()
                .map(GoogleResourceMapper::map)
                .collect(partitioningBy(Result::passed));

        //save only the  ones that passed the mapping process
        Set<PersonDb> passed = mappingResult.get(true).stream()
                .map(Result::data)
                .peek(person -> person.setAddedBy(currentUser))
                .collect(toSet());

        personService.saveAll(passed);

        //log failures
        logFailures(mappingResult.get(false));

    }

    private void logFailures(List<Result> results) {
        results.forEach(System.out::println);
    }

    @Override
    public void exportContacts() {

    }

    @Override
    public void syncWithAPI() {

    }
}