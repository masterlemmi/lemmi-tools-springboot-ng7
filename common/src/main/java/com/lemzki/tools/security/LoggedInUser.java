package com.lemzki.tools.security;

import com.lemzki.tools.security.model.User;

@FunctionalInterface
public interface LoggedInUser {
    User get();

     LoggedInUser ANONYMOUS = ()->User.ANONYMOUS;

}
