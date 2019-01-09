package com.lemzki.tools.people.db.exception;

import org.omg.SendingContext.RunTime;

public class GenderRequiredException extends RuntimeException {
    public GenderRequiredException(){
        super("Gender is a required input.");
    }
}
