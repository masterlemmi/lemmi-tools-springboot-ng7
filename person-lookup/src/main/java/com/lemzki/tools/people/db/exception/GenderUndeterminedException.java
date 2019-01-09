package com.lemzki.tools.people.db.exception;

public class GenderUndeterminedException extends RuntimeException{
    public GenderUndeterminedException(){
        super("Unable to Determine Gender");
    }
}
