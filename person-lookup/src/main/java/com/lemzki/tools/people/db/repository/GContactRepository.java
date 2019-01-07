package com.lemzki.tools.people.db.repository;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.lemzki.tools.people.db.mapper.impl.PersonMapper;
import com.lemzki.tools.people.db.model.Person;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


//GET https://people.googleapis.com/v1/people/me/connections?pageSize=2&personFields=urls%2Cphotos%2CphoneNumbers%2CuserDefined%2Cnames%2Crelations%2Cnicknames%2Cbirthdays%2CcoverPhotos%2CemailAddresses%2Cgenders&key={YOUR_API_KEY}

@Service
public class GContactRepository {

    private final static String ME="people/me";
    private final static String FIELDS ="urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders";

    PeopleService peopleService;

    public void getContacts() throws IOException {

        //response.getConnections().forEach(PersonMapper::mapGoogleResource);
    }


    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    public void rtestGet(String token) throws GeneralSecurityException, IOException {
        GoogleCredential credential = new GoogleCredential().setAccessToken(token);
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        peopleService = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("LemmiTools")
                .build();

        ListConnectionsResponse response = peopleService.people().connections().list(ME)
                .setPersonFields(FIELDS)
                .execute();
        System.out.println("");
    }


}