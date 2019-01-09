package com.lemzki.tools.people.db.controller.impl;

import com.lemzki.tools.people.db.controller.PeopleApiController;
import com.lemzki.tools.people.db.service.PeopleAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleApiControllerImpl implements PeopleApiController {

    @Autowired
    PeopleAPIService peopleAPIService;

    @Override
    public String exportContactsToGoogle() {
        return peopleAPIService.exportContactsToGoogle();
    }

    @Override
    public String importContactsFromGoogle() {
        return null;
    }
}
