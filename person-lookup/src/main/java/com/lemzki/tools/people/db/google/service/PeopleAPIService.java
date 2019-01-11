package com.lemzki.tools.people.db.google.service;

public interface PeopleAPIService {

    //get contacts from google.. convert and save to DB
    String importContactPersonsFromGoogle();

    String exportContactsToGoogle() throws InterruptedException;

    void syncLocalDBWithAPI() throws InterruptedException;
}
