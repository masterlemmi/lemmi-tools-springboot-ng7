package com.lemzki.tools.people.db.google.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.lemzki.tools.exception.UnauthenticatedSessionException;
import com.lemzki.tools.security.LoggedInUser;
import com.lemzki.tools.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceRetriever {

    @Autowired
    HttpTransport httpTransport;

    @Autowired
    JsonFactory jsonFactory;

    @Autowired
    LoggedInUser loggedInUser;

    public PeopleService get(){
        return new PeopleService.Builder(httpTransport,
                jsonFactory, googleCredential())
                .setApplicationName("LemmiTools")
                .build();
    }

    private HttpRequestInitializer googleCredential() {
        if(loggedInUser == null || loggedInUser.get() == User.ANONYMOUS){
            throw new UnauthenticatedSessionException();
        }
        String accessToken =  loggedInUser.get().getAccessToken();
        return new GoogleCredential().setAccessToken(accessToken);
    }
}
