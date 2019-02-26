package com.lemzki.tools.security;

import com.lemzki.tools.security.model.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class UserMapper {



    public static User mapFrom(OAuth2Authentication oAuth2Authentication) {
        User user = new User();
        @SuppressWarnings("unchecked")
        Map<String, String> details = (Map<String, String>) oAuth2Authentication.getUserAuthentication().getDetails();
        user.setEmail(details.get("email"));
        user.setGoogleId(details.get("id"));
        user.setName(details.get("name"));
        user.setLink(details.get("link"));
        user.setGender(details.get("gender"));
        user.setPicture(details.get("picture"));

        String accessToken = ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
        user.setAccessToken(accessToken);

        return user;
    }


}
