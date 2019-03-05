package com.lemzki.tools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component public class GooglePrincipalExtractor implements PrincipalExtractor {
    @Autowired private UserService userService;

    @Override public Object extractPrincipal(Map<String, Object> details) {
        String id = (String) details.get("id");

        Optional<User> optionalUser = userService.findByGoogleId((id));

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            User user = UserMapper.mapFrom(details);
            user.addRole(Role.USER);
            return userService.save(user);
        }
    }
}
