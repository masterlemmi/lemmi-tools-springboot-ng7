package com.lemzki.tools.security.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class GoogleConfig {

    @Bean
    JsonFactory jsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }

    @Bean
    HttpTransport httpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
        GoogleCredential googleCredential() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        String accessToken = ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
        return new GoogleCredential().setAccessToken(accessToken);
    }


    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public PeopleService peopleService() {
        PeopleService peopleService = null;
        try {
            peopleService = new PeopleService.Builder(httpTransport(),
                    jsonFactory(), googleCredential())
                    .setApplicationName("LemmiTools")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return peopleService;
    }
}
