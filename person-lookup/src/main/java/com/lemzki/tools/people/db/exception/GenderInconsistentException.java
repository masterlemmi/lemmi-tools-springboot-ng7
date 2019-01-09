package com.lemzki.tools.people.db.exception;

public class GenderInconsistentException extends RuntimeException {

    public GenderInconsistentException() {
        super("Unable to determine gender");
    }

    public GenderInconsistentException(String s) {
       super(s);
    }
}
