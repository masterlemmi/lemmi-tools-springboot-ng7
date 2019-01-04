package com.lemzki.tools.people.db.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Configuration
public class GoogleConfiguration {

    private JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    @Value("${google.api.client.id}")
    private String[] CLIENT_ID;

    public GoogleConfiguration() throws GeneralSecurityException, IOException {
    }

    @Bean
    public GoogleIdTokenVerifier googleIdTokenVerifier() {
        return new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                // Specify the CLIENT_ID of the app that accesses the backend:
                //.setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                .setAudience(Arrays.asList(CLIENT_ID))
                .build();
    }

  //  @Bean
    public PeopleService peopleService(){
        GoogleCredential credential = new GoogleCredential().setAccessToken(null);
        return new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("LemmiTools")
                .build();
    }
}
