package com.lemzki.tools.security.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.lemzki.tools.security.LoggedInUser;
import com.lemzki.tools.security.mapper.UserMapper;
import com.lemzki.tools.security.model.User;
import com.lemzki.tools.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

@Configuration
public class GoogleConfig {

    @Autowired
    UserService userService;

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
        String accessToken =  loggedInUser().get().getAccessToken();
        return new GoogleCredential().setAccessToken(accessToken);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LoggedInUser loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2Authentication){
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) auth;

            String googleId = ((Map<String, String>) oAuth2Authentication.getUserAuthentication().getDetails()).get("id");

            return () ->  userService.findByGoogleId(googleId).orElse(User.ANONYMOUS);
        }

        System.out.println("Logged in anonymously or wiht something else" + auth);
        return LoggedInUser.ANONYMOUS;
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
