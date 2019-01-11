package com.lemzki.tools.people.db.mapper.impl;

import com.google.api.services.people.v1.model.*;
import com.google.common.collect.Lists;
import com.lemzki.tools.people.db.enums.GenderE;
import com.lemzki.tools.people.db.model.PersonDb;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

public class GoogleContactUpdater {


    public static void updateContact(Person contact, PersonDb personDb) {
        contact.setNicknames(Lists.newArrayList(new Nickname().setValue(personDb.getNickname())));
        contact.setNames(Lists.newArrayList(new Name().setDisplayName(personDb.getName())));
        contact.setBirthdays(Lists.newArrayList(new Birthday().setDate(toGoogleDate(personDb.getDateOfBirth()))));

        contact.setEvents(updatedEvents(contact.getEvents(),  personDb.getDateOfDeath()));

        contact.setGenders(Lists.newArrayList(new Gender().setValue(personDb.getGender().toString())));

        //also execute Gender placeholder under relations field
        contact.setRelations(updatedGenderPlaceholder(contact.getRelations(), personDb.getGender()));
    }

    private static List<Relation> updatedGenderPlaceholder(List<Relation> relations, GenderE genderE) {
        if (CollectionUtils.isEmpty(relations)) {
           Relation gender = new Relation();
           gender.setType("Gender");
           gender.setPerson(genderE.toString());
            relations = Lists.newArrayList(gender);
        } else {
            relations.forEach(rel -> {
                if (rel.getType().equalsIgnoreCase("Gender")) {
                    rel.setPerson(genderE.toString());
                }
            });
        }
        return relations;
    }


    private static List<Event> updatedEvents(List<Event> eventList, LocalDate dateOfDeath) {
        if (CollectionUtils.isEmpty(eventList)) {
            Event death = new Event();
            death.setType("Death");
            death.setDate(toGoogleDate(dateOfDeath));
            eventList = Lists.newArrayList(death);
        } else {
            eventList.forEach(event -> {
                if (event.getType().equalsIgnoreCase("Death")) {
                    event.setDate(toGoogleDate(dateOfDeath));
                }
            });
        }
        return eventList;
    }

    private static Date toGoogleDate(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return null;
        Date date = new Date();
        date.setDay(dateOfBirth.getDayOfMonth());
        date.setMonth(dateOfBirth.getMonthValue());
        date.setYear(dateOfBirth.getYear());
        return date;
    }


}
