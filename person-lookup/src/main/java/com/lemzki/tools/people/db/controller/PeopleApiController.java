package com.lemzki.tools.people.db.controller;

public interface PeopleApiController {

    String exportContactsToGoogle() throws InterruptedException;

    String importContactsFromGoogle();
}
