package com.lemzki.tools.security;

import com.lemzki.tools.security.model.User;

@FunctionalInterface
public interface LoggedInUser {
    User getUser();

    static LoggedInUser anonymous(){
        return ()->{
            User user = new User();
            user.setName("Anonymous");
            return user;
        };
    }
}
