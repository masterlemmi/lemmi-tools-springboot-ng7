package com.lemzki.tools.people.db.exception;

public class RelationShipPersistenceException extends RuntimeException {
    private String message;
    public RelationShipPersistenceException(String s) {
        this.message = s;
    }
}
