package com.lemzki.tools.people.db.service;

public interface PeopleAPIService {

    //get contacts from google.. convert and save to DB
    void importContacts();

    void exportContacts();

    void syncWithAPI();


}
