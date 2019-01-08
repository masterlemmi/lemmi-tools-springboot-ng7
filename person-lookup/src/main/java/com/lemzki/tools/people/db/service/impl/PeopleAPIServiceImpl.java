package com.lemzki.tools.people.db.service.impl;


import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.lemzki.tools.people.db.mapper.impl.PersonMapper;
import com.lemzki.tools.people.db.service.PeopleAPIService;
import com.lemzki.tools.security.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
    public void importContacts() {
        List<Person> people = getList();
        people.stream()
                .map(PersonMapper::mapGoogleResource)
                .collect(toList());
    }

    @Override
    public void exportContacts() {

    }

    @Override
    public void syncWithAPI() {

    }
}