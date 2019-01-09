package com.lemzki.tools.security.exception;

public class UnauthenticatedSessionException extends RuntimeException {

    public UnauthenticatedSessionException(){
        super("Session is not Authenticated. You  miust sign in to view the resource");
    }
}
