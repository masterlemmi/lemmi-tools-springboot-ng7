package com.lemzki.tools.security;

@FunctionalInterface
public interface LoggedInUser {
    User get();

     LoggedInUser ANONYMOUS = ()->User.ANONYMOUS;

}
