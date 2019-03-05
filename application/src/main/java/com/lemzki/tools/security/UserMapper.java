package com.lemzki.tools.security;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class UserMapper {


    public static User mapFrom(OAuth2Authentication oAuth2Authentication) {

        @SuppressWarnings("unchecked")
        Map<String, Object> details = (Map<String, Object>) oAuth2Authentication.getUserAuthentication().getDetails();
        User user = mapFrom(details);
        String accessToken = ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
        user.setAccessToken(accessToken);

        return user;
    }

    public static User mapFrom(Map<String, Object> details) {
        User user = new User();
        user.setEmail((String) details.get("email"));
        user.setGoogleId((String) details.get("id"));
        user.setName((String) details.get("name"));
        user.setLink((String) details.get("link"));
        user.setGender((String) details.get("gender"));
        user.setPicture((String) details.get("picture"));
        return user;
    }


}
