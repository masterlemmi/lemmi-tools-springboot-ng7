package com.lemzki.tools.people.db.exception;




public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(){
        super("Unable to locate person resource.");
    }
}
