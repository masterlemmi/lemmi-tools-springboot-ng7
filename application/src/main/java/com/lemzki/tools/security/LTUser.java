package com.lemzki.tools.security;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LTUser {
    private String name;

    public static LTUser from(Principal principal) {
        OAuth2Authentication user = (OAuth2Authentication) principal;
        Map<String, String> userDetails = (LinkedHashMap<String, String>) user.getUserAuthentication().getDetails();
        return new LTUser(userDetails.get("name"));
    }

}