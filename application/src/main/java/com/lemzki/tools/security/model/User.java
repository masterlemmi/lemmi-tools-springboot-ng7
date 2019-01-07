package com.lemzki.tools.security.model;


import javax.persistence.*;

import lombok.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String googleId;
    private String name;
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public static User from(Principal principal) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        Map<String, String> userDetails = (LinkedHashMap<String, String>) oAuth2Authentication.getUserAuthentication().getDetails();
        User user = new User();
        user.setName(userDetails.get("name"));
        user.setEmail(userDetails.get("email"));
        user.setGoogleId(userDetails.get("id"));

        //autho keys
        String accessToken =( (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
        String refreshToken = "not yet found";

        return user;
    }
}
