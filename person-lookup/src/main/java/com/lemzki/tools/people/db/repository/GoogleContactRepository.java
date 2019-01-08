package com.lemzki.tools.people.db.repository;


import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


//GET https://people.googleapis.com/v1/people/me/connections?pageSize=2&personFields=urls%2Cphotos%2CphoneNumbers%2CuserDefined%2Cnames%2Crelations%2Cnicknames%2Cbirthdays%2CcoverPhotos%2CemailAddresses%2Cgenders&key={YOUR_API_KEY}

@Service
public class GoogleContactRepository {

    private final static String ME="people/me";
    private final static String FIELDS ="urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders";

    @Autowired
    PeopleService peopleService;


    public ListConnectionsResponse getList() throws IOException {
        return peopleService.people().connections().list(ME)
                .setPersonFields(FIELDS)
                .execute();
    }

   }